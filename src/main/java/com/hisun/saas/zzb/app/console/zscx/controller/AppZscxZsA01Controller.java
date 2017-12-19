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

import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxB01;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZs;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZsA01;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxB01Service;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxZsA01Service;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxZsService;
import com.hisun.saas.zzb.app.console.zscx.vo.AppZscxZsA01Vo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/appZscxZsA01")
public class AppZscxZsA01Controller extends BaseController{
    @Resource
    private AppZscxZsA01Service appZscxZsA01Service;

    @Resource
    private AppZscxB01Service appZscxB01Service;

    @Resource
    private AppZscxZsService appZscxZsService;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    private final static String DEFAULT_IMG_HEAD_PATH = "/WEB-INF/images/defaultHeadImage.png";

    @RequestMapping(value = "/")
    public ModelAndView list(){

        return new ModelAndView("saas/zzb/app/console/zscx/zscxManage");



    }
    @RequestMapping(value="/ajax/list")
    public ModelAndView getList(String b01Id,String zsId,String xmQuery,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            String zwmc = "";
            if(zsId!=null && !zsId.equals("")){
                query.add(CommonRestrictions.and(" appZscxZs.id = :zsId",
                        "zsId", zsId));
                 AppZscxZs appZscxZs = this.appZscxZsService.getByPK(zsId);
                zwmc = appZscxZs.getZwmc();

            }
            if(xmQuery!=null && !xmQuery.equals("")){
                query.add(CommonRestrictions.and(" xm like:xmQuery", "xmQuery", "%"+ xmQuery+ "%"));
            }

            Long total = appZscxZsA01Service.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("px"));
//            orderBy.add(CommonOrder.desc("name"));
            List<AppZscxZsA01> a01List = appZscxZsA01Service.list(query, orderBy, pageNum, pageSize);
            PagerVo< AppZscxZsA01> pagerVo = new PagerVo< AppZscxZsA01>(a01List, total.intValue(), pageNum, pageSize);
            map.put("pager", pagerVo);
            map.put("b01Id", b01Id);
            map.put("zsId", zsId);
            map.put("xmQuery", xmQuery);
            map.put("zwmc", zwmc);

            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/zscx/a01/list", map);

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
             AppZscxZsA01 a01 = this.appZscxZsA01Service.getByPK(AssetStatusId);
            if(a01 != null){
                this.appZscxZsA01Service.delete(a01);
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
             AppZscxZsA01 a01 = this.appZscxZsA01Service.getByPK(id);
             AppZscxZsA01Vo a01Vo = new  AppZscxZsA01Vo();
            if (a01 == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(a01Vo, a01);
            map.put("a01Vo", a01Vo);
            map.put("b01Id", a01.getAppZscxZs().getAppZscxB01().getId());
            map.put("zsId", a01.getAppZscxZs().getId());
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "查看失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/zscx/a01/view", map);
    }



    @RequestMapping("/{id}/photo")
    public HttpEntity<byte[]> getPhoto (@PathVariable("id")String id,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
         AppZscxZsA01 appZscxZsA01 = this.appZscxZsA01Service.getByPK(id);
        if(appZscxZsA01.getZpPath()!=null){
            String zpRealPath = uploadAbsolutePath+appZscxZsA01.getZpPath();
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
