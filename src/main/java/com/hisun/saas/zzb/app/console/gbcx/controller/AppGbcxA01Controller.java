package com.hisun.saas.zzb.app.console.gbcx.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbcx.entity.AppGbcxA01;
import com.hisun.saas.zzb.app.console.gbcx.entity.AppGbcxB01;
import com.hisun.saas.zzb.app.console.gbcx.service.AppGbcxA01Service;
import com.hisun.saas.zzb.app.console.gbcx.service.AppGbcxB01Service;
import com.hisun.saas.zzb.app.console.gbcx.vo.AppGbcxA01Vo;
import com.hisun.saas.zzb.app.console.shpc.entity.*;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/appGbcxA01")
public class AppGbcxA01Controller extends BaseController{
    @Resource
    private AppGbcxA01Service appGbcxA01Service;

    @Resource
    private AppGbcxB01Service appGbcxB01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    private final static String DEFAULT_IMG_HEAD_PATH = "/WEB-INF/images/defaultHeadImage.png";

    @RequestMapping(value = "/")
    public ModelAndView list(){

        return new ModelAndView("saas/zzb/app/console/gbcx/gbcxManage");



    }
    @RequestMapping(value="/ajax/list")
    public ModelAndView getList(String b01Id,String xmQuery,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            String b0101 = "";
            if(b01Id!=null && !b01Id.equals("1")){
                query.add(CommonRestrictions.and(" appGbcxB01.id = :b01Id",
                        "b01Id", b01Id));
                AppGbcxB01 appGbcxB01 = this.appGbcxB01Service.getByPK(b01Id);
                b0101 = appGbcxB01.getB0101();
            }
            if(xmQuery!=null && !xmQuery.equals("")){
                query.add(CommonRestrictions.and(" xm like:xmQuery", "xmQuery", "%"+ xmQuery+ "%"));
            }

            Long total = appGbcxA01Service.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
//            orderBy.add(CommonOrder.desc("lastUpdateTime"));
//            orderBy.add(CommonOrder.desc("name"));
            List<AppGbcxA01> a01List = appGbcxA01Service.list(query, orderBy, pageNum, pageSize);
            PagerVo<AppGbcxA01> pagerVo = new PagerVo<AppGbcxA01>(a01List, total.intValue(), pageNum, pageSize);
            map.put("pager", pagerVo);
            map.put("b01Id", b01Id);
            map.put("xmQuery", xmQuery);
            map.put("b0101", b0101);

            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/gbcx/a01/list", map);

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
                String fileDir = uploadAbsolutePath +appGbcxA01Service.ATTS_PATH;
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
//                    String tmplateWordPath = fileDir+File.separator+"sha01.docx";
//                    WordUtil wordUtil = WordUtil.newInstance();
//                    Map<String,String> dataMap = wordUtil.convertMapByTemplate(savePath,tmplateWordPath,"");
//                    sha01Service.saveFromWordDataMap(userLoginDetails.getTenant(),dataMap,shpcId);

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
        AppGbcxA01Vo vo = new AppGbcxA01Vo();
        Integer maxPx = appGbcxA01Service.getMaxPx(b01Id);
        if(maxPx != null){
            vo.setA01Px(maxPx+1);
        }else{
            vo.setA01Px(1);
        }

        return new ModelAndView("/saas/zzb/app/console/gbcx/a01/add","vo",vo);
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
            AppGbcxA01 a01 = this.appGbcxA01Service.getByPK(id);
            AppGbcxA01Vo a01Vo = new AppGbcxA01Vo();
            if (a01 == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(a01Vo, a01);
            map.put("a01", a01Vo);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbcx/a01/edit", map);
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
            AppGbcxA01 a01 = this.appGbcxA01Service.getByPK(AssetStatusId);
            if(a01 != null){
                this.appGbcxA01Service.delete(a01);
            }
            map.put("success", true);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }

    @RequestMapping(value = "ajax/view")
    public ModelAndView view(@RequestParam(value="id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AppGbcxA01 a01 = this.appGbcxA01Service.getByPK(id);
            AppGbcxA01Vo a01Vo = new AppGbcxA01Vo();
            if (a01 == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(a01Vo, a01);
            map.put("a01Vo", a01Vo);
            map.put("b01Id", a01.getAppGbcxB01().getId());
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "查看失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbcx/a01/view", map);
    }

    /**
     * 保存信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute AppGbcxA01Vo a01Vo, HttpServletRequest req, @RequestParam(value="clFile",required = false) MultipartFile clFile) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        AppGbcxA01 a01 = null;
        int newPx = a01Vo.getA01Px();
        int oldPx = 0;
        Integer oldPxInteger = this.appGbcxA01Service.getMaxPx(a01Vo.getAppGbcxB01Id());
        if(oldPxInteger != null){
            oldPx = oldPxInteger.intValue();
        }else{
            oldPx = 1;
        }

        try {
            if (a01Vo != null) {
                String id = a01Vo.getId();
                if (id != null && id.length() > 0) {//修改
                    a01 = this.appGbcxA01Service.getByPK(id);
                    oldPx = a01.getA01Px();
                } else {//新增
                    a01 = new AppGbcxA01();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(a01, a01Vo);

                a01.setTenant(userLoginDetails.getTenant());
                if(clFile!=null && !clFile.isEmpty()) {

                    String fileName = clFile.getOriginalFilename();
                    if (fileName.endsWith(".doc") || fileName.endsWith(".DOC")
                            || fileName.endsWith(".docx") || fileName.endsWith(".DOCX")) {
                        String fileDir = uploadAbsolutePath + appGbcxA01Service.ATTS_PATH;
                        File _fileDir = new File(fileDir);
                        if (_fileDir.exists() == false) {
                            _fileDir.mkdirs();
                        }
                        //附件存储路径
                        String savePath = appGbcxA01Service.ATTS_PATH + UUIDUtil.getUUID() + "_" + fileName;
                        String rPath =uploadAbsolutePath+ savePath;

                        try {
                            FileOutputStream fos = new FileOutputStream(new File(rPath));
                            fos.write(clFile.getBytes());
                            fos.flush();
                            fos.close();
                            a01.setZpPath(savePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new GenericException(e);
                        }
                    }
                }

                if(oldPx!=newPx) {
                    this.appGbcxA01Service.updatePx(a01Vo.getAppGbcxB01Id(),oldPx,newPx);
                }
                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(a01, userLoginDetails, "update");
                    this.appGbcxA01Service.update(a01);
                } else {
                    BeanTrans.setBaseProperties(a01, userLoginDetails, "save");
                    this.appGbcxA01Service.save(a01);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping("/{id}/photo")
    public HttpEntity<byte[]> getPhoto (@PathVariable("id")String id,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        AppGbcxA01 appGbcxA01 = this.appGbcxA01Service.getByPK(id);
        if(appGbcxA01.getZpPath()!=null){
            String zpRealPath = uploadAbsolutePath+appGbcxA01.getZpPath();
            File file = new File(zpRealPath);
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                StreamUtils.copy(fis,response.getOutputStream());
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                return new HttpEntity(HttpStatus.OK);
            }else{
                //为空或者没有返回默认图片
                File defaultfile = new File(request.getServletContext().getRealPath(DEFAULT_IMG_HEAD_PATH));
                FileInputStream fis = new FileInputStream(defaultfile);
                StreamUtils.copy(fis,response.getOutputStream());
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                return new HttpEntity(HttpStatus.OK);
            }
        }else{
            //为空或者没有返回默认图片
            File defaultfile = new File(request.getServletContext().getRealPath(DEFAULT_IMG_HEAD_PATH));
            FileInputStream fis = new FileInputStream(defaultfile);
            StreamUtils.copy(fis,response.getOutputStream());

            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            return new HttpEntity(HttpStatus.OK);
        }

    }
}
