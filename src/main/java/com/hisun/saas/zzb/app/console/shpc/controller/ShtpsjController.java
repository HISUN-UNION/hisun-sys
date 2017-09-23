package com.hisun.saas.zzb.app.console.shpc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.shpc.entity.Shtpsj;
import com.hisun.saas.zzb.app.console.shpc.service.ShtpsjService;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import com.hisun.saas.zzb.app.console.shpc.vo.ShtpVo;
import com.hisun.saas.zzb.app.console.shpc.vo.ShtpsjVo;
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
@RequestMapping("/zzb/app/sh/tpsj")
public class ShtpsjController extends BaseController {
    @Autowired
    private ShtpsjService shtpsjService;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest req,@RequestParam(value="shtpId")String shtpId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String tprxm ="";
            String shpcId = "";
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" shtp.id = :shtpId", "shtpId", shtpId));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));

            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("tp"));

            Long total = this.shtpsjService.count(query);
            List<Shtpsj> shtpsjs = this.shtpsjService.list(query, orderBy, pageNum,
                    pageSize);
            List<ShtpsjVo> shtpsjVos = new ArrayList<ShtpsjVo>();
            if (shtpsjs != null) {// entity ==> vo
                for (Shtpsj shtpsj : shtpsjs) {
                    if(tprxm.equals("")){
                        tprxm = shtpsj.getShtp().getTpr_xm();
                    }
                    if(shpcId.equals("")){
                        shpcId = shtpsj.getSha01().getShpc().getId();
                    }
                    ShtpsjVo vo = new ShtpsjVo();
                    Sha01Vo sha01Vo = new Sha01Vo();
                    BeanUtils.copyProperties(vo, shtpsj);
                    BeanUtils.copyProperties(sha01Vo, shtpsj.getSha01());
                    vo.setSha01Vo(sha01Vo);
                    shtpsjVos.add(vo);
                }
            }
            PagerVo<ShtpsjVo> pager = new PagerVo<ShtpsjVo>(shtpsjVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("shtpId", shtpId);
            map.put("shpcId", shpcId);
            map.put("tprxm", tprxm);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/tpqk/list", map);
    }

    
}
