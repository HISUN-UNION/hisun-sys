package com.hisun.saas.zzb.app.console.gbcx.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbcx.entity.AppGbcxB01;
import com.hisun.saas.zzb.app.console.gbcx.service.AppGbcxB01Service;
import com.hisun.saas.zzb.app.console.gbcx.vo.AppGbcxB01Vo;
import com.hisun.saas.zzb.app.console.gbcx.vo.B01TreeVo;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.DateUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WebUtil;
import com.hisun.util.WordConvertUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/appGbcxB01")
public class AppGbcxB01Controller extends BaseController{

    @Resource
    private AppGbcxB01Service appGbcxB01Service;

    @RequestMapping(value = "/")
    public ModelAndView list(){

        return new ModelAndView("saas/zzb/app/console/unuse");



    }
    @RequestMapping(value="/ajax/load/tree")
    public @ResponseBody
    Map<String,Object> loadTree(HttpServletRequest request)throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("dataType"));
            orderBy.add(CommonOrder.asc("px"));
            List<AppGbcxB01> appGbcxB01s = this.appGbcxB01Service.list(query, orderBy);

            List<B01TreeVo> b01TreeVoList = Lists.newArrayList();
            String contextPath = WebUtil.getRequest().getContextPath();
            if(appGbcxB01s != null){
                for (AppGbcxB01 b01 : appGbcxB01s) {
                    // 自定义分组信息

                        B01TreeVo b01TreeVo = new B01TreeVo();
                        b01TreeVo.setId(b01.getId());
                        if(b01.getParentB01() != null) {
                            b01TreeVo.setpId(b01.getParentB01().getId());
                        }else{
                            b01TreeVo.setpId("");
                        }
                        b01TreeVo.setName(b01.getB0101());
                        b01TreeVo.setHref(b01.getId());
                        b01TreeVo.setDataType(b01.getDataType()+"");
                        b01TreeVo.setNoR(false);
                        b01TreeVo.setDropInner(true);
//                        if(StringUtils.isNotBlank(inst.getMonitor().getIcon())){
//                            b01TreeVo.setIcon(contextPath + "/monitor/ajax/icon/" + inst.getMonitor().getId() + ".jpg?OWASP_CSRFTOKEN=" + request.getSession().getAttribute("OWASP_CSRFTOKEN"));
//                        } else {
//                            b01TreeVo.setIcon(contextPath + MonitorVo.DEFAULT_PATH);
//                        }
                        b01TreeVoList.add(b01TreeVo);

                }
            }
            // 添加根节点
//            b01TreeVoList.add(new B01TreeVo().oneRoot());
            // 添加未分组节点
            map.put("customTree", b01TreeVoList);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e,e);
            map.put("success", false);
            map.put("message", "服务器忙，请稍后再试！");
        }
        return map;
    }



    /**
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/ajax/addOrEdit")
    public ModelAndView addOrEdit(@RequestParam(value="dataType",required=false)String dataType,@RequestParam(value="parentId",required=false)String parentId,@RequestParam(value="id",required=false)String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        AppGbcxB01Vo vo = new AppGbcxB01Vo();
        try {
            if(id==null || id.equals("")) {
                Integer maxPx = appGbcxB01Service.getMaxPx(parentId);
                if (maxPx != null) {
                    vo.setPx(maxPx + 1);
                } else {
                    vo.setPx(1);
                }
                vo.setDataType(Integer.parseInt(dataType));
                vo.setParentId(parentId);
            }else{
                AppGbcxB01 appGbcxB01 = this.appGbcxB01Service.getByPK(id);
                if (appGbcxB01 == null) {
                    logger.error("数据不存在");
                    throw new GenericException("数据不存在");
                }
                BeanUtils.copyProperties(vo, appGbcxB01);
                if (appGbcxB01.getParentB01() != null) {
                    parentId = appGbcxB01.getParentB01().getId();
                }
                vo.setParentId(parentId);
            }
        } catch(Exception e) {
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbcx/addAndEditB01","vo",vo);
    }


    /**
     * 保存部务会信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute AppGbcxB01Vo appGbcxB01Vo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        AppGbcxB01 appGbcxB01 = null;
        int newPx = appGbcxB01Vo.getPx();
        int oldPx = 0;
        Integer oldPxInteger = this.appGbcxB01Service.getMaxPx(appGbcxB01Vo.getParentId());
        if(oldPxInteger != null){
            oldPx = oldPxInteger.intValue();
        }else{
            oldPx = 1;
        }

        try {
            if (appGbcxB01Vo != null) {
                String id = appGbcxB01Vo.getId();
                if (id != null && id.length() > 0) {//修改
                    appGbcxB01 = this.appGbcxB01Service.getByPK(id);
                    oldPx = appGbcxB01.getPx();
                } else {//新增
                    appGbcxB01 = new AppGbcxB01();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(appGbcxB01, appGbcxB01Vo);
                String parentId = appGbcxB01Vo.getParentId();
                AppGbcxB01 parentAppGbcxB01 = null;
                if(parentId!=null && !parentId.equals("")){
                    parentAppGbcxB01 = this.appGbcxB01Service.getByPK(parentId);
                    appGbcxB01.setParentB01(parentAppGbcxB01);
                }
                appGbcxB01.setTenant(userLoginDetails.getTenant());
               

                if(oldPx!=newPx) {
                    this.appGbcxB01Service.updatePx(appGbcxB01Vo.getParentId(),oldPx,newPx);
                }
                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(appGbcxB01, userLoginDetails, "update");
                    this.appGbcxB01Service.update(appGbcxB01);
                } else {
                    BeanTrans.setBaseProperties(appGbcxB01, userLoginDetails, "save");
                    this.appGbcxB01Service.save(appGbcxB01);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "保存出错");
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping(value="/delete/{id}")
    public @ResponseBody Map<String,Object> delete(@PathVariable("id") String id)throws GenericException{
        Map<String,Object> map = Maps.newHashMap();
        try {
            if (StringUtils.isNoneBlank(id)) {
                appGbcxB01Service .deleteByPK(id);
                map.put("success", true);
            } else {
                map.put("success", false);
                map.put("message", "删除失败，参数有误");
            }
        }catch(Exception e){
            logger.error(e, e);
        }
        return map;
    }


}
