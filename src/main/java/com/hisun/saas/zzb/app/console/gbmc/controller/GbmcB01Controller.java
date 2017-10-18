package com.hisun.saas.zzb.app.console.gbmc.controller;

import com.aspose.words.Document;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcB01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcB01Vo;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.DateUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbmc/b01")
public class GbmcB01Controller extends BaseController{

    @Resource
    private GbMcB01Service gbMcB01Service;
    @Resource
    private GbMcService gbMcService;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest req, @RequestParam(value="mcid")String mcid,String b0101Query,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" gbMc.id = :mcid", "mcid", mcid));
            if(b0101Query!=null && !b0101Query.equals("")){
                query.add(CommonRestrictions.and(" b0101 like :b0101Query", "b0101Query",  "%"+b0101Query+ "%"));
            }
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));

            Long total = this.gbMcB01Service.count(query);
            List<GbMcB01> sha01s = this.gbMcB01Service.list(query,orderBy, pageNum,
                    pageSize);
            List<GbMcB01Vo> shpcVos = new ArrayList<GbMcB01Vo>();
            if (sha01s != null) {// entity ==> vo
                for (GbMcB01 sha01 : sha01s) {
                    GbMcB01Vo vo = new GbMcB01Vo();
                    BeanUtils.copyProperties(vo, sha01);
                    shpcVos.add(vo);
                }
            }
            PagerVo<GbMcB01Vo> pager = new PagerVo<GbMcB01Vo>(shpcVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("mcid", mcid);
            map.put("b0101Query", b0101Query);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbmc/b01/list", map);
    }

    @RequestMapping(value="/ajax/execute")
    public @ResponseBody
    Map<String,Object> importExcel(String mcid, String token, @RequestParam(value="attachFile",required=false) MultipartFile file, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> map = new HashMap<String,Object>();
        if(file==null || file.isEmpty()){
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;
        }

        try{
            String fileName = file.getOriginalFilename();
            if(fileName.endsWith(".doc") ||fileName.endsWith(".DOC") ||fileName.endsWith(".docx") ||fileName.endsWith(".DOCX") ){
                String fileDir = uploadAbsolutePath +GbMcB01Service.ATTS_PATH;
                File _fileDir = new File(fileDir);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                String savePath = fileDir + UUIDUtil.getUUID()+"_"+fileName;

                try {
                    File tmpFile = new File(savePath);
                    FileOutputStream fos = new FileOutputStream(tmpFile);
                    fos.write(file.getBytes());
                    fos.flush();
                    fos.close();

                    GbMc gbMc = this.gbMcService.getByPK(mcid);
                    //处理上传word
                    WordUtil wordUtil = WordUtil.newInstance();
                    InputStream stream = new FileInputStream(new File(savePath));
                    Document doc = new Document(stream);
                    GbMcB01 b01 = null;
                    String b0101 = "";
                    int px = 1;
                    for (Paragraph para : (Iterable<Paragraph>) doc.getChildNodes(NodeType.PARAGRAPH, true)) {
                        //取得目录...之前的数据
                        if(para.getText().indexOf("...")>0) {
                            b0101 = para.getText().substring(0,para.getText().indexOf("."));
                            b01= new GbMcB01();
                            b01.setB0101(b0101);
                            b01.setPx(px);
                            b01.setTenant(userLoginDetails.getTenant());
                            b01.setGbMc(gbMc);
                            this.gbMcB01Service.save(b01);
                            px++;
                        }
                    }
                    //删除临时文件
                    tmpFile.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new GenericException(e);
                }
            }else{
                map.put("code", -1);
                map.put("message", "请上传word");
                return map;
            }
        }catch(Exception e){
            map.put("code", -1);
            map.put("message", "读取文件错误，请检查word是否能正确打开");
            return map;
        }
        try{

        }catch(GenericException e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", e.getMessage());
            return map;
        }catch(Exception e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", "系统错误，请联系管理员");
            return map;
        }
        map.put("code", 1);
        return map;
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
            GbMcB01 gbMcB01 = this.gbMcB01Service.getByPK(id);
            if(gbMcB01 != null){
                this.gbMcB01Service.delete(gbMcB01);
            }
            map.put("success", true);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }
}