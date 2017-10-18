package com.hisun.saas.zzb.app.console.shpc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.apiregister.entity.ApiRegister;
import com.hisun.saas.zzb.app.console.apiregister.service.ApiRegisterService;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhouying on 2017/9/8.
 */
@Controller
@RequestMapping("/zzb/app/console/bwh")
public class BwhController extends BaseController {
    @Resource
    private ShpcService shpcService;
    @Resource
    private ApiRegisterService apiRegisterService;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req, String pId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            //临时处理,将当前服务IP,端口,context写入ApiRegister
            List<ApiRegister> apiRegisters = this.apiRegisterService.list();
            if(apiRegisters!=null && apiRegisters.size()>0){
                for(ApiRegister apiRegister : apiRegisters){
                    apiRegister.setIp(req.getServerName());
                    apiRegister.setPort(""+req.getServerPort());
                    apiRegister.setContext(req.getContextPath());
                    this.apiRegisterService.update(apiRegister);
                }
            }
            CommonConditionQuery query = new CommonConditionQuery();
           // query.add(CommonRestrictions.and(" shlx = :shlx", "shlx", Shpc.SHLX_BWH));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("pcsj"));

            Long total = this.shpcService.count(query);
            List<Shpc> shpcs = this.shpcService.list(query, orderBy, pageNum,
                    pageSize);
            List<ShpcVo> shpcVos = new ArrayList<ShpcVo>();
            if (shpcs != null) {// entity ==> vo
                for (Shpc shpc : shpcs) {
                    ShpcVo vo = new ShpcVo();
                    BeanUtils.copyProperties(vo, shpc);
                    vo.setPcsjValue(DateUtil.formatDateByFormat(shpc.getPcsj(),"yyyyMMdd"));
                    vo.setA01Count(shpc.getSha01s().size());
                    shpcVos.add(vo);
                }
            }
            PagerVo<ShpcVo> pager = new PagerVo<ShpcVo>(shpcVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/bwh/list", map);
    }
    /**
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView add() {
        return new ModelAndView("/saas/zzb/app/console/bwh/add");
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
            Shpc shpc = this.shpcService.getByPK(id);
            ShpcVo shpcVo = new ShpcVo();
            if (shpc == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(shpcVo, shpc);
            shpcVo.setPcsjValue(DateUtil.formatDateByFormat(shpc.getPcsj(), "yyyyMMdd"));
            map.put("shpc", shpcVo);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/bwh/edit", map);
    }


    /**
     * 调转到修改页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody Map<String, Object> delete(@PathVariable("id")String AssetStatusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Shpc shpc = this.shpcService.getByPK(AssetStatusId);
            if(shpc != null){
                this.shpcService.delete(shpc);
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
    public @ResponseBody Map<String, Object> save(@ModelAttribute ShpcVo shpcVo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        Shpc shpc = null;
        try {
            if (shpcVo != null) {
                String id = shpcVo.getId();
                if (id != null && id.length() > 0) {//修改
                    shpc = this.shpcService.getByPK(id);
                } else {//新增
                    shpc = new Shpc();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(shpc, shpcVo);
                if(shpcVo.getPcsjValue()!=null && !shpcVo.getPcsjValue().equals("")) {
                    Date pcsjDate = DateUtil.parseDate(shpcVo.getPcsjValue().toString(), "yyyyMMdd");
                    shpc.setPcsj(pcsjDate);
                }
                shpc.setTenant(userLoginDetails.getTenant());
                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(shpc, userLoginDetails, "update");
                    this.shpcService.update(shpc);
                } else {
                    BeanTrans.setBaseProperties(shpc, userLoginDetails, "save");
                    this.shpcService.save(shpc);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }
    /**
     * 调转到修改页面
     * @return
     */
    @RequestMapping(value ="/changeShZt/{id}")
    public @ResponseBody Map<String, Object> changeShZt(@PathVariable("id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            String shZtValue = "";
            Shpc shpc = this.shpcService.getByPK(id);
            if(shpc.getShZt()==0){
                shpc.setShZt(1);
                shZtValue = "已上会";
            }else{
                shpc.setShZt(0);
                shZtValue = "未上会";
            }
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            BeanTrans.setBaseProperties(shpc, userLoginDetails, "update");
            this.shpcService.update(shpc);
            map.put("success", true);
            map.put("shZtValue", shZtValue);
        } catch (Exception e) {
            map.put("success", false);
            throw new GenericException(e);
        }
        return map;
    }
}
