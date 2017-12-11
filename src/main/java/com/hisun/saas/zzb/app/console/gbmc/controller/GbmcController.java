package com.hisun.saas.zzb.app.console.gbmc.controller;

import com.aspose.words.*;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbmc.entity.*;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gbrmspbService;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcVo;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.saas.zzb.app.console.util.GzjlUtil;
import com.hisun.util.CompressUtil;
import com.hisun.util.JacksonUtil;
import com.hisun.util.WordUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbmc")
public class GbmcController extends BaseController{


    @Autowired
    private GbMcService gbMcService;
    @Autowired
    private GbMcA01Service gbMcA01Service;
    @Autowired
    private GbMcA01gbrmspbService gbMcA01gbrmspbService;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req, String mcQuery,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            // query.add(CommonRestrictions.and(" shlx = :shlx", "shlx", Shpc.SHLX_BWH));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            if(mcQuery!=null && !mcQuery.equals("")){
                query.add(CommonRestrictions.and(" mc like:mcQuery", "mcQuery", "%"+ mcQuery+ "%"));
            }

//            CommonOrderBy orderBy = new CommonOrderBy();
//            orderBy.add(CommonOrder.desc("pcsj"));

            Long total = this.gbMcService.count(query);
            List<GbMc> gbmcs = this.gbMcService.list(query, null, pageNum,
                    pageSize);
            List<GbMcVo> gbMcVos = new ArrayList<GbMcVo>();
            if (gbmcs != null) {// entity ==> vo
                for (GbMc gbMc : gbmcs) {
                    GbMcVo vo = new GbMcVo();
                    BeanUtils.copyProperties(vo, gbMc);
                    vo.setA01Count(this.gbMcService.getA01Count(gbMc.getId()));
                    //vo.setA01Count(gbMc.getGbMcA01s().size());
                    gbMcVos.add(vo);
                }
            }
            PagerVo<GbMcVo> pager = new PagerVo<GbMcVo>(gbMcVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("mcQuery", mcQuery);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbmc/list", map);
    }
    @RequestMapping(value = "/add")
    public ModelAndView add() {
        GbMcVo vo = new GbMcVo();
        vo.setPx(99);
        return new ModelAndView("/saas/zzb/app/console/gbmc/add","gbMc",vo);

    }


    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(value="id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            GbMc gbMc = this.gbMcService.getByPK(id);
            GbMcVo gbMcVo = new GbMcVo();
            if (gbMc == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(gbMcVo, gbMc);
            map.put("gbMc", gbMcVo);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbmc/edit", map);
    }


    /**
     * 调转到修改页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            GbMc gbMc = this.gbMcService.getByPK(id);
            if(gbMc != null){
                this.gbMcService.delete(gbMc);
            }
            map.put("success", true);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }

    /**
     * 保存名册信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute GbMcVo gbMcVo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        GbMc gbMc = null;
        try {
            if (gbMcVo != null) {
                String id = gbMcVo.getId();
                if (id != null && id.length() > 0) {//修改
                    gbMc = this.gbMcService.getByPK(id);
                } else {//新增
                    gbMc = new GbMc();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(gbMc, gbMcVo);
                gbMc.setTenant(userLoginDetails.getTenant());
                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(gbMc, userLoginDetails, "update");
                    this.gbMcService.update(gbMc);
                } else {
                    BeanTrans.setBaseProperties(gbMc, userLoginDetails, "save");

                    //处理word列表数据
                    WordUtil wordUtil = WordUtil.newInstance();
                    String wordPath = "/Users/zhouying/Desktop/湘西州/测试数据/5名册列表/2/2州直单位领导干部名册.docx";
                    String wordPathTemplate = "/Users/zhouying/Desktop/湘西州/测试数据/5名册列表/2/gbmca01.docx";

                    List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
                    Document templateDoc = wordUtil.read(wordPathTemplate);
                    Map<String, Integer> templateMap = wordUtil.generateTemplateMap(templateDoc);

                    Document document = wordUtil.read(wordPath);
                    NodeCollection collection = document.getChildNodes(NodeType.TABLE, true);
                    for(Iterator<Table> tables = collection.iterator(); tables.hasNext();){
                        Table table = tables.next();
                        NodeCollection cells = table.getChildNodes(NodeType.CELL,true);
                        dataList.add(wordUtil.convertMapByTemplate(cells,templateMap));
                    }
                    this.gbMcService.saveFromWordDataMap(gbMc,GbMc.YML,dataList);

                    //处理JSON数据
                    String zipFilePath = "/Users/zhouying/Desktop/湘西州/测试数据/5名册列表/2/personsinfodata.zip";
                    String tmpFilePath ="/Users/zhouying/Desktop/湘西州/测试数据/5名册列表/2/test/";
                    CompressUtil.unzip(zipFilePath,tmpFilePath);
                    //处理照片
                    String photoZipFilePath="/Users/zhouying/Desktop/湘西州/测试数据/5名册列表/2/personsimgs.zip";
                    String tmpPhotoFilePath="/Users/zhouying/Desktop/湘西州/测试数据/5名册列表/2/photo/";
                    CompressUtil.unzip(photoZipFilePath,tmpPhotoFilePath);
                    this.gbMcA01Service.updateA01FromYwJson(gbMc.getId(),tmpFilePath,tmpPhotoFilePath);

                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }


    private void parseYwmcWord(String sourceeWordPath) throws Exception{

        //String tmplateWordPath = uploadAbsolutePath + GbMcA01Service.IMPORT_DOC_TEMPLATE;
        WordUtil wordUtil = WordUtil.newInstance();

        String wordPath = "/Users/zhouying/Desktop/湘西州/测试数据/5名册列表/2/2州直单位领导干部名册.docx";
        String wordPathTemplate = "/Users/zhouying/Desktop/湘西州/测试数据/5名册列表/2/gbmca01.docx";

        Document templateDoc = wordUtil.read(wordPathTemplate);
        Map<String, Integer> templateMap = wordUtil.generateTemplateMap(templateDoc);

        Document document = wordUtil.read(wordPath);
        NodeCollection collection = document.getChildNodes(NodeType.TABLE, true);
        for(Iterator<Table> tables = collection.iterator(); tables.hasNext();){
            Table table = tables.next();

            NodeCollection cells = table.getChildNodes(NodeType.CELL,true);
            for(Iterator<Cell> it = cells.iterator(); it.hasNext();){
                Cell cell = it.next();

            }
        }
    }



}
