package com.hisun.saas.zzb.app.console.shpc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.entity.Shtp;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shpc.service.ShtpService;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;
import com.hisun.saas.zzb.app.console.shpc.vo.ShtpVo;
import com.hisun.util.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Controller
@RequestMapping("/zzb/app/sh/tp")
public class ShtpController extends BaseController {
    @Autowired
    private ShtpService shtpService;
    @Autowired
    private ShpcService shpcService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest req, String pId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            // query.add(CommonRestrictions.and(" shlx = :shlx", "shlx", Shpc.SHLX_BWH));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
//            CommonOrderBy orderBy = new CommonOrderBy();
//            orderBy.add(CommonOrder.desc("updateDate"));

            Long total = this.shpcService.count(query);
            List<Shpc> shpcs = this.shpcService.list(query, null, pageNum,
                    pageSize);
            List<ShpcVo> shpcVos = new ArrayList<ShpcVo>();
            if (shpcs != null) {// entity ==> vo
                for (Shpc shpc : shpcs) {
                    ShpcVo vo = new ShpcVo();
                    BeanUtils.copyProperties(vo, shpc);
                    vo.setPcsjValue(DateUtil.formatDateByFormat(shpc.getPcsj(),"yyyyMMdd"));
                    vo.setTpCount(shpc.getShtps().size());
                    shpcVos.add(vo);
                }
            }
            PagerVo<ShpcVo> pager = new PagerVo<ShpcVo>(shpcVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/tp/list", map);
    }

    @RequestMapping("/tplist")
    public ModelAndView tplist(HttpServletRequest req,@RequestParam(value="shpcId")String shpcId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String pcmc = "";
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" shpc.id = :shpcId", "shpcId",shpcId));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
//            CommonOrderBy orderBy = new CommonOrderBy();
//            orderBy.add(CommonOrder.desc("updateDate"));

            Long total = this.shtpService.count(query);
            List<Shtp> shtps = this.shtpService.list(query, null, pageNum,
                    pageSize);
            List<ShtpVo> shtpVos = new ArrayList<ShtpVo>();
            if (shtps != null) {// entity ==> vo
                for (Shtp shtp : shtps) {
                    if(pcmc.equals("")){
                        pcmc =  shtp.getShpc().getPcmc();
                    }
                    ShtpVo vo = new ShtpVo();
                    BeanUtils.copyProperties(vo, shtp);
                    vo.setTp_sj(DateUtil.formatDateByFormat(shtp.getTp_sj(),"yyyyMMdd"));
                    vo.setTpqkCount(shtp.getShtpsjs().size());
                    shtpVos.add(vo);
                }
            }
            PagerVo<ShtpVo> pager = new PagerVo<ShtpVo>(shtpVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("shpcId", shpcId);
            map.put("pcmc", pcmc);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/tp/tpList", map);
    }


}
