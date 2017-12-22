package com.hisun.saas.zzb.app.console.exchange.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.exchange.entity.ExchangeActuator;
import com.hisun.saas.zzb.app.console.exchange.service.ExchangeActuatorService;
import com.hisun.saas.zzb.app.console.exchange.vo.ExchangeActuatorVo;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/exchange")
public class ExchangeActuatorController extends BaseController {
    @Resource
    private ExchangeActuatorService exchangeActuatorService;

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req,String identificationQuery,String statusQuery,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));

            if(identificationQuery!=null && !identificationQuery.equals("")){
                query.add(CommonRestrictions.and(" identification like:identificationQuery", "identificationQuery", "%"+ identificationQuery+ "%"));
            }
            if(statusQuery!=null && !statusQuery.equals("") && !statusQuery.equals("-1")){
                query.add(CommonRestrictions.and(" status = :statusQuery", "statusQuery", Integer.valueOf(statusQuery)));
            }
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("sort"));

            Long total = this.exchangeActuatorService.count(query);
            List<ExchangeActuator> exchangeActuators = this.exchangeActuatorService.list(query, orderBy, pageNum,
                    pageSize);
            List<ExchangeActuatorVo> exchangeActuatorVos = new ArrayList<ExchangeActuatorVo>();
            if (exchangeActuators != null) {
                for (ExchangeActuator exchangeActuator : exchangeActuators) {
                    ExchangeActuatorVo vo = new ExchangeActuatorVo();
                    BeanUtils.copyProperties(vo, exchangeActuator);
                    exchangeActuatorVos.add(vo);
                }
            }
            PagerVo<ExchangeActuatorVo> pager = new PagerVo<ExchangeActuatorVo>(exchangeActuatorVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("identificationQuery", identificationQuery);
            map.put("statusQuery", statusQuery);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/exchange/list", map);
    }

    /**
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/ajax/addOrEdit")
    public ModelAndView addOrEdit(@RequestParam(value="dataType",required=false)String dataType,@RequestParam(value="parentId",required=false)String parentId,@RequestParam(value="id",required=false)String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        ExchangeActuatorVo vo = new ExchangeActuatorVo();
        try {
            if(id==null || id.equals("")) {
                Integer maxPx = exchangeActuatorService.getMaxPx();
                if (maxPx != null) {
                    vo.setSort(maxPx + 1);
                } else {
                    vo.setSort(1);
                }
            }else{
                ExchangeActuator exchangeActuator = this.exchangeActuatorService.getByPK(id);
                if (exchangeActuator == null) {
                    logger.error("数据不存在");
                    throw new GenericException("数据不存在");
                }
            }
        } catch(Exception e) {
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/exchange/addAndEditImport","vo",vo);
    }
    /**
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView add() throws Exception{
        ExchangeActuatorVo vo = new ExchangeActuatorVo();
        Integer maxPx = exchangeActuatorService.getMaxPx();
        if (maxPx != null) {
            vo.setSort(maxPx + 1);
        } else {
            vo.setSort(1);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vo", vo);
        return new ModelAndView("/saas/zzb/app/console/exchange/add",map);
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
            ExchangeActuator exchangeActuator = this.exchangeActuatorService.getByPK(id);
            if (exchangeActuator == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            ExchangeActuatorVo vo = new ExchangeActuatorVo();
            BeanUtils.copyProperties(vo, exchangeActuator);
            map.put("vo", vo);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/exchange/edit", map);
    }

    /**
     * 保存部务会信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute ExchangeActuatorVo exchangeActuatorVo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        ExchangeActuator exchangeActuator = null;
       
      

        try {
            if (exchangeActuatorVo != null) {
                String id = exchangeActuatorVo.getId();
                if (id != null && id.length() > 0) {//修改
                    exchangeActuator = this.exchangeActuatorService.getByPK(id);
                } else {//新增
                    exchangeActuator = new ExchangeActuator();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(exchangeActuator, exchangeActuatorVo);
                exchangeActuator.setTenant(userLoginDetails.getTenant());

                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(exchangeActuator, userLoginDetails, "update");
                    this.exchangeActuatorService.update(exchangeActuator);
                } else {
                    BeanTrans.setBaseProperties(exchangeActuator, userLoginDetails, "save");
                    this.exchangeActuatorService.save(exchangeActuator);
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
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            ExchangeActuator exchangeActuator = this.exchangeActuatorService.getByPK(id);
            if(exchangeActuator != null){
                this.exchangeActuatorService.delete(exchangeActuator);
            }
            map.put("success", true);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }

    @RequestMapping(value = "/ajax/importData")
    public @ResponseBody Map<String, Object> importData(@RequestParam(value="id")String id, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        ExchangeActuator exchangeActuator = this.exchangeActuatorService.getByPK(id);
        int sourceType = exchangeActuator.getSourceType();
        if(sourceType==ExchangeActuator.source_gwyglxt){//从公务员管理系统(浙大网新)

        }else if(sourceType==ExchangeActuator.source_zzzhywpt){//从组织综合业务平台(广州三零)

        }else if(sourceType==ExchangeActuator.source_gbglxt){//从干部管理系统(长沙远望)

        }
        try {
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "导入出错");
            throw new GenericException(e);
        }
        return map;
    }
    @RequestMapping(value = "/ajax/clearData")
    public @ResponseBody Map<String, Object> clearData(@RequestParam(value="id")String id, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        ExchangeActuator exchangeActuator = this.exchangeActuatorService.getByPK(id);
        int sourceType = exchangeActuator.getSourceType();
        if(sourceType==ExchangeActuator.source_gwyglxt){//从公务员管理系统(浙大网新)

        }else if(sourceType==ExchangeActuator.source_zzzhywpt){//从组织综合业务平台(广州三零)

        }else if(sourceType==ExchangeActuator.source_gbglxt){//从干部管理系统(长沙远望)

        }
        try {
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "导入出错");
            throw new GenericException(e);
        }
        return map;
    }
}
