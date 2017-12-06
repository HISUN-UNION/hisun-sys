package com.hisun.saas.zzb.app.console.zscx.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxB01;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxB01Service;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZs;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxB01Service;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxZsService;
import com.hisun.saas.zzb.app.console.zscx.vo.AppZscxZsVo;
import com.hisun.util.UUIDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/appZscxZs")
public class AppZscxZsController extends BaseController{
    @Resource
    private AppZscxZsService appZscxZsService;

    @Resource
    private AppZscxB01Service appZscxB01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    private final static String DEFAULT_IMG_HEAD_PATH = "/WEB-INF/images/defaultHeadImage.png";

    @RequestMapping(value="/ajax/list")
    public ModelAndView getList(String b01Id,String xmQuery,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            String b0101 = "";
            if(b01Id!=null && !b01Id.equals("1")){
                query.add(CommonRestrictions.and(" appZscxB01.id = :b01Id",
                        "b01Id", b01Id));
                AppZscxB01 appZscxB01 = this.appZscxB01Service.getByPK(b01Id);
                b0101 = appZscxB01.getB0101();
            }
            if(xmQuery!=null && !xmQuery.equals("")){
                query.add(CommonRestrictions.and(" xm like:xmQuery", "xmQuery", "%"+ xmQuery+ "%"));
            }

            Long total = appZscxZsService.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("appZscxB01.id"));
            orderBy.add(CommonOrder.asc("appZscxB01.px"));
//            orderBy.add(CommonOrder.desc("name"));
            List<AppZscxZs> zsList = appZscxZsService.list(query, orderBy, pageNum, pageSize);
            List<AppZscxZsVo> appZscxZsVos = new ArrayList<AppZscxZsVo>();
            for(AppZscxZs appZscxZs : zsList){
                AppZscxZsVo appZscxZsVo = new AppZscxZsVo();
                BeanUtils.copyProperties(appZscxZsVo, appZscxZs);
                appZscxZsVo.setB0101(appZscxZs.getAppZscxB01().getB0101());
                appZscxZsVos.add(appZscxZsVo);
            }
            PagerVo<AppZscxZsVo> pagerVo = new PagerVo<AppZscxZsVo>(appZscxZsVos, total.intValue(),
                    pageNum, pageSize);

            if(b01Id.equals("1")){
                b01Id = "";
            }
            map.put("pager", pagerVo);
            map.put("b01Id", b01Id);
            map.put("xmQuery", xmQuery);
            map.put("b0101", b0101);

            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/zscx/zs/list", map);

    }

    @RequestMapping(value="/ajax/execute")
    public @ResponseBody
    Map<String,Object> importExcel(String b01Id, String token, @RequestParam(value="attachFile",required=false) MultipartFile file, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
                String fileDir = uploadAbsolutePath +appZscxZsService.ATTS_PATH;
                File _fileDir = new File(fileDir);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                String savePath = fileDir + UUIDUtil.getUUID()+"_"+fileName;

                try {
                    FileOutputStream fos = new FileOutputStream(new File(savePath));
                    fos.write(file.getBytes());
                    fos.flush();
                    fos.close();

//                    //处理上传文件
//                    //先将word转成Map
//                    String tmplateWordPath = fileDir+File.separator+"shzs.docx";
//                    WordUtil wordUtil = WordUtil.newInstance();
//                    Map<String,String> dataMap = wordUtil.convertMapByTemplate(savePath,tmplateWordPath,"");
//                    shzsService.saveFromWordDataMap(userLoginDetails.getTenant(),dataMap,shpcId);

//                    Shpc shpc = this.shpcService.getByPK(shpcId);
//                    if (shpc != null) {
//                        shpc.setFilePath(savePath);
//                        BeanTrans.setBaseProperties(shpc, userLoginDetails, "update");
//                        this.shpcService.update(shpc);
//                    }
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
            e.printStackTrace();
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
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(String b01Id) throws Exception{
        AppZscxZsVo vo = new AppZscxZsVo();

        return new ModelAndView("/saas/zzb/app/console/zscx/zs/add","vo",vo);
    }

    /**
     * 调转到修改页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:edit")
    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(value="id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AppZscxZs zs = this.appZscxZsService.getByPK(id);
            AppZscxZsVo zsVo = new AppZscxZsVo();
            if (zs == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(zsVo, zs);
            map.put("zs", zsVo);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/zscx/zs/edit", map);
    }


    /**
     * 调转到修改页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("id")String AssetStatusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            AppZscxZs zs = this.appZscxZsService.getByPK(AssetStatusId);
            if(zs != null){
                this.appZscxZsService.delete(zs);
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
     * 保存信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute AppZscxZsVo zsVo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        AppZscxZs zs = null;

        try {
            if (zsVo != null) {
                String id = zsVo.getId();
                if (id != null && id.length() > 0) {//修改
                    zs = this.appZscxZsService.getByPK(id);

                } else {//新增
                    zs = new AppZscxZs();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(zs, zsVo);

                zs.setTenant(userLoginDetails.getTenant());



                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(zs, userLoginDetails, "update");
                    this.appZscxZsService.update(zs);
                } else {
                    BeanTrans.setBaseProperties(zs, userLoginDetails, "save");
                    this.appZscxZsService.save(zs);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }

  
}
