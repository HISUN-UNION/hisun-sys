package com.hisun.saas.zzb.app.console.gbmc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcVo;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbmc")
public class GbmcController extends BaseController{


    @Autowired
    private GbMcService gbMcService;

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
                    this.gbMcService.save(gbMc);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }
}
