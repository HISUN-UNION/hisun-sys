package com.hisun.saas.zzb.app.console.gbtj.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gbtj.service.GbtjService;
import com.hisun.saas.zzb.app.console.gbtj.vo.GbtjVo;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gbtj.vo.GbtjVo;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbtj")
public class GbtjController extends BaseController {
    @Resource
    private GbtjService gbtjService;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req, String pId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("px"));

            Long total = this.gbtjService.count(query);
            List<Gbtj> gbtjs = this.gbtjService.list(query, orderBy, pageNum,
                    pageSize);
            List<GbtjVo> gbtjVos = new ArrayList<GbtjVo>();
            if (gbtjs != null) {
                for (Gbtj gbtj : gbtjs) {
                    GbtjVo vo = new GbtjVo();
                    BeanUtils.copyProperties(vo, gbtj);
                    gbtjVos.add(vo);
                }
            }
            PagerVo<GbtjVo> pager = new PagerVo<GbtjVo>(gbtjVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbtj/list", map);
    }


    @RequestMapping(value = "/add")
    public ModelAndView add() {
        GbtjVo vo = new GbtjVo();
        vo.setPx(99);
        return new ModelAndView("/saas/zzb/app/console/gbtj/add","gbtj",vo);

    }


    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(value="id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Gbtj gbtj = this.gbtjService.getByPK(id);
            GbtjVo gbtjVo = new GbtjVo();
            if (gbtj == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(gbtjVo, gbtj);
            map.put("gbtj", gbtjVo);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbtj/edit", map);
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
            Gbtj gbtj = this.gbtjService.getByPK(AssetStatusId);
            if(gbtj != null){
                this.gbtjService.delete(gbtj);
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
     * 保存部务会信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute GbtjVo gbtjVo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        Gbtj gbtj = null;
        try {
            if (gbtjVo != null) {
                String id = gbtjVo.getId();
                if (id != null && id.length() > 0) {//修改
                    gbtj = this.gbtjService.getByPK(id);
                } else {//新增
                    gbtj = new Gbtj();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(gbtj, gbtjVo);
                gbtj.setTenant(userLoginDetails.getTenant());
                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(gbtj, userLoginDetails, "update");
                    this.gbtjService.update(gbtj);
                } else {
                    BeanTrans.setBaseProperties(gbtj, userLoginDetails, "save");
                    this.gbtjService.save(gbtj);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }


}
