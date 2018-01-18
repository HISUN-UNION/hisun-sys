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
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01Query;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01QueryService;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA02Service;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA01QueryVo;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA01Vo;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.saas.zzb.app.console.util.GzjlUtil;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZs;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxZsService;
import com.hisun.util.JacksonUtil;
import com.hisun.util.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
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
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/asetA01Query")
public class AppAsetA01QueryController extends BaseController {
    @Resource
    private AppAsetA01QueryService appAsetA01QueryService;

    @Resource
    private AppAsetA01Service appAsetA01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    private final static String DEFAULT_IMG_HEAD_PATH = "/WEB-INF/images/defaultHeadImage.png";


    @RequestMapping(value = "/")
    public ModelAndView getList(String queryId, String xmQuery,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        String queryName = "";
        try {

            List<Object> paramList = Lists.newArrayList();
            String hql = " from AppAsetA01 a01  inner join a01.appAsetA02s a02  inner join a02.appBsetB01 b01  inner join b01.appBsetFl2B01s fltob01  where 1=1 ";
            if (StringUtils.isNotBlank(queryId)) {
                AppAsetA01Query a01 = this.appAsetA01QueryService.getByPK(queryId);
                queryName = a01.getQueryName();
                hql = hql + a01.getQueryCondition();
            }


            hql = hql + " and a01.tombstone =? order by fltob01.px,b01.px,a02.jtlPx ";
            paramList.add(0);
            int total = this.appAsetA01Service.count("select  count(distinct a01.id) " + hql, paramList);
            List<AppAsetA01> appAsetA01s = this.appAsetA01Service.list("select  DISTINCT(a01) " + hql, paramList, pageNum,
                    pageSize);
            List<AppAsetA01Vo> appAsetA01Vos = new ArrayList<AppAsetA01Vo>();
            for (AppAsetA01 a01 : appAsetA01s) {
                AppAsetA01Vo vo = new AppAsetA01Vo();
                BeanUtils.copyProperties(vo, a01);
                appAsetA01Vos.add(vo);
            }
            PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(appAsetA01Vos, total, pageNum, pageSize);

            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("querySort"));

            List<AppAsetA01Query> appAsetA01Querys = this.appAsetA01QueryService.list(query, orderBy);

            map.put("pager", pagerVo);
            map.put("queryId", queryId);
            map.put("queryName", queryName);
            map.put("appAsetA01Querys", appAsetA01Querys);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/asetA01/query/list", map);

    }

    @RequestMapping(value = "/queryList")
    public ModelAndView queryList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("querySort"));

            Long total = this.appAsetA01QueryService.count(query);
            List<AppAsetA01Query> appAsetA01Querys = this.appAsetA01QueryService.list(query, orderBy, pageNum,
                    pageSize);
            List<AppAsetA01QueryVo> appAsetA01QueryVos = new ArrayList<AppAsetA01QueryVo>();
            for (AppAsetA01Query a01 : appAsetA01Querys) {
                AppAsetA01QueryVo vo = new AppAsetA01QueryVo();
                BeanUtils.copyProperties(vo, a01);
                appAsetA01QueryVos.add(vo);
            }
            PagerVo<AppAsetA01QueryVo> pagerVo = new PagerVo<AppAsetA01QueryVo>(appAsetA01QueryVos, total.intValue(), pageNum, pageSize);

            map.put("pager", pagerVo);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/asetA01/query/queryList", map);

    }

    /**
     * 调转到新增页面
     *
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView add() throws Exception {
        AppAsetA01QueryVo vo = new AppAsetA01QueryVo();
        Integer maxPx = appAsetA01QueryService.getMaxPx();
        if(maxPx != null){
            vo.setQuerySort(maxPx+1);
        }else{
            vo.setQuerySort(1);
        }

        return new ModelAndView("/saas/zzb/app/console/asetA01/query/addOrEdit", "vo", vo);
    }

    @RequestMapping(value = "/selectB01")
    public @ResponseBody Map<String, Object> getSelectB01(
           String queryId) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (queryId != null && queryId.trim().equals("") == false) {
                AppAsetA01Query a01 = this.appAsetA01QueryService.getByPK(queryId);
                String queryJson = a01.getQueryJson();
                AppAsetA01QueryVo conditionVo  = new AppAsetA01QueryVo();
                if(queryJson!=null && !("").equals(queryJson)) {
                    conditionVo = JacksonUtil.nonDefaultMapper().fromJson(queryJson, AppAsetA01QueryVo.class);
                }

                List<String> list = Lists.newArrayList();
                if(conditionVo.getB01Id()!=null && !conditionVo.getB01Id().equals("")) {
                    String[] b01Ids = conditionVo.getB01Id().split(";");
                    for (String b01Id : b01Ids) {
                        list.add(b01Id);
                    }
                }
                map.put("data", list);
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }
    /**
     * 调转到修改页面
     *
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:edit")
    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(value = "id") String id) {
        AppAsetA01QueryVo vo = new AppAsetA01QueryVo();
        try {
            AppAsetA01Query a01 = this.appAsetA01QueryService.getByPK(id);
            if(a01 != null){
               // BeanUtils.copyProperties(vo, a01);
                String queryJson = a01.getQueryJson();
                if(queryJson!=null && !("").equals(queryJson)) {
                    vo = JacksonUtil.nonDefaultMapper().fromJson(queryJson, AppAsetA01QueryVo.class);
                }
                vo.setId(a01.getId());
                vo.setQueryName(a01.getQueryName());
                vo.setQuerySort(a01.getQuerySort());
                vo.setQueryCondition(a01.getQueryCondition());
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }

         return new ModelAndView("/saas/zzb/app/console/asetA01/query/addOrEdit", "vo", vo);
    }


    @RequestMapping(value = "/savaAsGbmc")
    public
    @ResponseBody
    Map<String, Object> savaAsGbmc(String queryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AppAsetA01Query a01Query = this.appAsetA01QueryService.getByPK(queryId);
            this.appAsetA01QueryService.saveAsGbmc(a01Query);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "另存为干部名册失败！");
            throw new GenericException(e);
        }
        return map;

    }

    /**
     * 调转到修改页面
     *
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:delete")
    @RequestMapping(value = "/delete/{id}")
    public
    @ResponseBody
    Map<String, Object> delete(@PathVariable("id") String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AppAsetA01Query a01 = this.appAsetA01QueryService.getByPK(id);
            if (a01 != null) {
                this.appAsetA01QueryService.delete(a01);
            }
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }

    @RequestMapping(value = "/view")
    public ModelAndView view(@RequestParam(value = "id") String id, String queryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AppAsetA01 a01 = this.appAsetA01Service.getByPK(id);
            AppAsetA01Vo a01Vo = new AppAsetA01Vo();
            if (a01 == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(a01Vo, a01);

            map.put("a01Vo", a01Vo);
            map.put("queryId", queryId);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "查看失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/asetA01/query/view", map);
    }

    /**
     * 保存信息
     *
     * @return
     */
    @RequestMapping(value = "/save")
    public
    @ResponseBody
    Map<String, Object> save(@ModelAttribute AppAsetA01QueryVo a01Vo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        AppAsetA01Query a01 = null;
        int newPx = a01Vo.getQuerySort();
        int oldPx = 0;
        Integer oldPxInteger = this.appAsetA01QueryService.getMaxPx();
        if(oldPxInteger != null){
            oldPx = oldPxInteger.intValue();
        }else{
            oldPx = 1;
        }

        try {
            if (a01Vo != null) {
                String id = a01Vo.getId();
                if (id != null && id.length() > 0) {//修改
                    a01 = this.appAsetA01QueryService.getByPK(id);
                    oldPx = a01.getQuerySort();
                } else {//新增
                    a01 = new AppAsetA01Query();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(a01, a01Vo);

                a01.setTenant(userLoginDetails.getTenant());
                if(oldPx!=newPx) {
                    this.appAsetA01QueryService.updatePx(oldPx,newPx);
                }
                String tjjsondataValue = JacksonUtil.nonDefaultMapper().toJson(a01Vo);
                a01.setQueryJson(tjjsondataValue);
                a01.setQueryCondition(getHql(a01Vo));
                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(a01, userLoginDetails, "update");
                    this.appAsetA01QueryService.update(a01);
                } else {
                    BeanTrans.setBaseProperties(a01, userLoginDetails, "save");
                    this.appAsetA01QueryService.save(a01);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }

    //得到queryCondition
    private String getHql(AppAsetA01QueryVo a01Vo){
        StringBuffer queryCondition = new StringBuffer();
        if(StringUtils.isNotBlank(a01Vo.getB01Id())){//机构
           String b01IdQuery = "";
            if(a01Vo.getB01Id()!=null && !a01Vo.getB01Id().equals("")) {
                String[] b01Ids = a01Vo.getB01Id().split(";");
                for (String b01Id : b01Ids) {
                   if(b01IdQuery.equals("")){
                       b01IdQuery = "'"+b01Id+"'";
                   }else{
                       b01IdQuery = b01IdQuery+",'"+b01Id+"'";
                   }
                }
            }
            queryCondition.append(" and b01.id in("+b01IdQuery+")");
        }
        //========================处理年龄
        if(StringUtils.isNotBlank(a01Vo.getNlStart()) && StringUtils.isNotBlank(a01Vo.getNlEnd())){
            queryCondition.append(" and  a01.nl>="+a01Vo.getNlStart() +" and a01.nl<="+a01Vo.getNlEnd());
        }
        if(StringUtils.isNotBlank(a01Vo.getNlStart()) && !StringUtils.isNotBlank(a01Vo.getNlEnd())){
            queryCondition.append(" and  a01.nl>="+a01Vo.getNlStart());
        }
        if(!StringUtils.isNotBlank(a01Vo.getNlStart()) && StringUtils.isNotBlank(a01Vo.getNlEnd())){
            queryCondition.append(" and a01.nl<="+a01Vo.getNlEnd());
        }
        //========================处理年龄
        if(StringUtils.isNotBlank(a01Vo.getXm())){
            queryCondition.append(" and a01.xm like'%"+a01Vo.getXm()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getXb())){
            queryCondition.append(" and a01.xb ='"+a01Vo.getXb()+"'");
        }
        if(StringUtils.isNotBlank(a01Vo.getMz())){
            queryCondition.append(" and a01.mz like'%"+a01Vo.getMz()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getZw())){
            queryCondition.append(" and a01.zw like'%"+a01Vo.getZw()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getCsd())){
            queryCondition.append(" and a01.csd like'%"+a01Vo.getCsd()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getJg())){
            queryCondition.append(" and a01.jg like'%"+a01Vo.getJg()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getQrzxl())){
            queryCondition.append(" and a01.qrzxl like'%"+a01Vo.getQrzxl()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getQrzxw())){
            queryCondition.append(" and a01.qrzxw like'%"+a01Vo.getQrzxw()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getZzxl())){
            queryCondition.append(" and a01.zzxl like'%"+a01Vo.getZzxl()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getZzxw())){
            queryCondition.append(" and a01.zzxw like'%"+a01Vo.getZzxw()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getXrzw())){
            queryCondition.append(" and a01.xrzw like'%"+a01Vo.getXrzw()+"%'");
        }

        if(StringUtils.isNotBlank(a01Vo.getQrzZy())){
            queryCondition.append(" and a01.qrzZy like'%"+a01Vo.getQrzZy()+"%'");
        }
        if(StringUtils.isNotBlank(a01Vo.getZzZy())){
            queryCondition.append(" and a01.zzZy like'%"+a01Vo.getZzZy()+"%'");
        }
        return queryCondition.toString();
    }

}
