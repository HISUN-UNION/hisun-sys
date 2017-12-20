package com.hisun.saas.zzb.app.console.aset.controller;

import com.google.common.collect.Lists;
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
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA02;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA02Service;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA02Vo;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA01Vo;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.DateUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/zzb/app/console/asetA01")
public class AppAsetA01Controller extends BaseController{
    @Resource
    private AppAsetA01Service appAsetA01Service;
    @Resource
    private AppAsetA02Service appAsetA02Service;
    @Resource
    private AppBsetB01Service appBsetB01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    private final static String DEFAULT_IMG_HEAD_PATH = "/WEB-INF/images/defaultHeadImage.png";

    @RequestMapping(value = "/")
    public ModelAndView list(){

        return new ModelAndView("saas/zzb/app/console/asetA01/a01Manage");



    }
    @RequestMapping(value="/ajax/list")
    public ModelAndView getList(String b01Id,String xmQuery,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        try {
            List<Object> paramList = Lists.newArrayList();
            String hql = " from AppAsetA01 a01 left join a01.appAsetA02s a02 where ";
            String b0101 = "";
            if(StringUtils.isNotBlank(b01Id)) {
                if(b01Id.equals("allA01")){
                    hql = hql+" 1<>1";
                }else{
                    paramList.add(b01Id);
                    hql = hql+" a02.appBsetB01.id = ?";
                    AppBsetB01 appBsetB01 = this.appBsetB01Service.getByPK(b01Id);
                    b0101 = appBsetB01.getB0101();
                }

            }

            if(xmQuery!=null && !xmQuery.equals("")){
                paramList.add("%"+ xmQuery+ "%");
                hql = hql+" and a01.xm like ?";
            }
            hql = hql+" and a01.tombstone =? order by a02.jtlPx,a01.a01Px";
            paramList.add(0);
            int total = this.appAsetA01Service.count("select  count(distinct a01.id) "+hql,paramList);
            List<AppAsetA01> appAsetA01s = this.appAsetA01Service.list("select  DISTINCT(a01) "+hql,paramList, pageNum,
                    pageSize);
            List<AppAsetA01Vo> appAsetA01Vos = new ArrayList<AppAsetA01Vo>();
            for (AppAsetA01 a01 : appAsetA01s) {
                AppAsetA01Vo vo = new AppAsetA01Vo();
                BeanUtils.copyProperties(vo, a01);
//                List<AppAsetA02Vo> appAsetA02Vos = new ArrayList<AppAsetA02Vo>();
//                List<AppAsetA02> appAsetA02s = a01.getAppAsetA02s();
//                if(appAsetA02s!=null){
//                    for(AppAsetA02 a02 : appAsetA02s){
//                        AppAsetA02Vo a02Vo = new AppAsetA02Vo();
//                        BeanUtils.copyProperties(a02Vo, a02);
//                        a02Vo.setB0101(a02.getAppBsetB01().getB0101());
//                        appAsetA02Vos.add(a02Vo);
//                    }
//                }
//                vo.setAppAsetA02Vos(appAsetA02Vos);
                appAsetA01Vos.add(vo);
            }
            PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(appAsetA01Vos, total, pageNum, pageSize);

            map.put("pager", pagerVo);
            map.put("b01Id", b01Id);
            map.put("xmQuery", xmQuery);
            map.put("b0101", b0101);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e,e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/asetA01/a01/list", map);

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

//        try{
//            String fileName = file.getOriginalFilename();
//            if(fileName.endsWith(".doc") ||fileName.endsWith(".DOC") ||fileName.endsWith(".docx") ||fileName.endsWith(".DOCX") ){
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    throw new GenericException(e);
//                }
//            }else{
//                map.put("code", -1);
//                map.put("message", "请上传word");
//                return map;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//            map.put("code", -1);
//            map.put("message", "读取文件错误，请检查word是否能正确打开");
//            return map;
//        }
//        try{
//
//        }catch(GenericException e){
//            logger.error(e, e);
//            map.put("code", -1);
//            map.put("message", e.getMessage());
//            return map;
//        }catch(Exception e){
//            logger.error(e, e);
//            map.put("code", -1);
//            map.put("message", "系统错误，请联系管理员");
//            return map;
//        }
//        map.put("code", 1);
        return map;
    }

    /**
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(String b01Id) throws Exception{
        AppAsetA01Vo vo = new AppAsetA01Vo();
//        Integer maxPx = appGbcxA01Service.getMaxPx(b01Id);
//        if(maxPx != null){
//            vo.setA01Px(maxPx+1);
//        }else{
//            vo.setA01Px(1);
//        }

        return new ModelAndView("/saas/zzb/app/console/asetA01/a01/add","vo",vo);
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
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/asetA01/a01/edit", map);
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
            AppAsetA01 a01 = this.appAsetA01Service.getByPK(AssetStatusId);
            if(a01 != null){
                this.appAsetA01Service.delete(a01);
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
//            AppAsetA01 a01 = this.appGbcxA01Service.getByPK(id);
//            AppAsetA01Vo a01Vo = new AppAsetA01Vo();
//            if (a01 == null) {
//                logger.error("数据不存在");
//                throw new GenericException("数据不存在");
//            }
//            BeanUtils.copyProperties(a01Vo, a01);
//            map.put("a01Vo", a01Vo);
//            map.put("b01Id", "");
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "查看失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/asetA01/a01/view", map);
    }

    /**
     * 保存信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute AppAsetA01Vo a01Vo, HttpServletRequest req, @RequestParam(value="clFile",required = false) MultipartFile clFile) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
//        AppAsetA01 a01 = null;
//        int newPx = a01Vo.getA01Px();
//        int oldPx = 0;
//        Integer oldPxInteger = this.appGbcxA01Service.getMaxPx(a01Vo.getAppGbcxB01Id());
//        if(oldPxInteger != null){
//            oldPx = oldPxInteger.intValue();
//        }else{
//            oldPx = 1;
//        }
//
//        try {
//            if (a01Vo != null) {
//                String id = a01Vo.getId();
//                if (id != null && id.length() > 0) {//修改
//                    a01 = this.appGbcxA01Service.getByPK(id);
//                    oldPx = a01.getA01Px();
//                } else {//新增
//                    a01 = new AppAsetA01();
//                }
//                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
//                BeanUtils.copyProperties(a01, a01Vo);
//
//                a01.setTenant(userLoginDetails.getTenant());
//                if(clFile!=null && !clFile.isEmpty()) {
//
//                    String fileName = clFile.getOriginalFilename();
//                    if (fileName.endsWith(".doc") || fileName.endsWith(".DOC")
//                            || fileName.endsWith(".docx") || fileName.endsWith(".DOCX")) {
//                        String fileDir = uploadAbsolutePath + appGbcxA01Service.ATTS_PATH;
//                        File _fileDir = new File(fileDir);
//                        if (_fileDir.exists() == false) {
//                            _fileDir.mkdirs();
//                        }
//                        //附件存储路径
//                        String savePath = appGbcxA01Service.ATTS_PATH + UUIDUtil.getUUID() + "_" + fileName;
//                        String rPath =uploadAbsolutePath+ savePath;
//
//                        try {
//                            FileOutputStream fos = new FileOutputStream(new File(rPath));
//                            fos.write(clFile.getBytes());
//                            fos.flush();
//                            fos.close();
//                            a01.setZpPath(savePath);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            throw new GenericException(e);
//                        }
//                    }
//                }
//
//                if(oldPx!=newPx) {
//                    this.appGbcxA01Service.updatePx(a01Vo.getAppGbcxB01Id(),oldPx,newPx);
//                }
//                if (id != null && id.length() > 0) {
//                    BeanTrans.setBaseProperties(a01, userLoginDetails, "update");
//                    this.appGbcxA01Service.update(a01);
//                } else {
//                    BeanTrans.setBaseProperties(a01, userLoginDetails, "save");
//                    this.appGbcxA01Service.save(a01);
//                }
//                map.put("success", true);
//            }
//        } catch (Exception e) {
//            throw new GenericException(e);
//        }
        return map;
    }

    @RequestMapping("/{id}/photo")
    public HttpEntity<byte[]> getPhoto (@PathVariable("id")String id,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        AppAsetA01 appAsetA01 = this.appAsetA01Service.getByPK(id);
        if(appAsetA01.getZpPath()!=null){
            String zpRealPath = uploadAbsolutePath+appAsetA01.getZpPath();
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
