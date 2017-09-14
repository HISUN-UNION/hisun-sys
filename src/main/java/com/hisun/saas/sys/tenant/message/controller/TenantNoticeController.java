package com.hisun.saas.sys.tenant.message.controller;

import com.google.common.collect.Maps;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.tenant.message.vo.TenantNoticeVo;
import com.hisun.saas.sys.tenant.message.entity.TenantNotice;
import com.hisun.saas.sys.tenant.message.service.TenantNoticeService;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>类名称：TenantNoticeController</p>
 * <p>类描述：</p>
 * <p>公司：湖南海数互联信息技术有限公司</p>
 *
 * @创建人：lihaiming
 * @创建时间：15/11/20 下午4:40
 * @创建人联系方式：lihm_gz@30wish.net
 */
@Controller
@RequestMapping("/sys/tenant/notice")
public class TenantNoticeController extends BaseController {

    @Autowired
    private TenantNoticeService noticeService;

    @RequestMapping(value="/list")
    public ModelAndView listNotice(HttpServletRequest req,
                                   @RequestParam(value="pageNum",defaultValue="1")int pageNum, @RequestParam(value="pageSize",defaultValue="20") int pageSize) throws GenericException {

        Map<String, Object> map = Maps.newHashMap();

        try{
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tenant.id = :id", "id", UserLoginDetailsUtil.getUserLoginDetails().getTenantId()));
            Long total = noticeService.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
            //orderBy.add(CommonOrder.asc("sort"));
            List<TenantNotice> resultList = noticeService.list(query, orderBy, pageNum, pageSize);
            PagerVo<TenantNotice> pager = new PagerVo<TenantNotice>(resultList, total.intValue(), pageNum, pageSize);
            map.put("pager", pager);
        }catch(Exception e){
            logger.error(e);
            throw new GenericException(e);
        }
        return new ModelAndView("saas/sys/notice/listNotice",map);
    }

    @RequestMapping(value="/all/list")
    public ModelAndView allListNotice(HttpServletRequest req,
                                      @RequestParam(value="pageNum",defaultValue="1")int pageNum, @RequestParam(value="pageSize",defaultValue="20") int pageSize) throws GenericException{

        Map<String, Object> map = Maps.newHashMap();

        try{
            CommonConditionQuery query = new CommonConditionQuery();
            //query.add(CommonRestrictions.and(" pId = :pId ", "pId", pId));
            query.add(CommonRestrictions.and(" pushWay = :pushWay ", "pushWay", Short.valueOf("2")));
            query.add(CommonRestrictions.and(" status = :status ", "status", Boolean.TRUE));
            query.add(CommonRestrictions.and(" tenant.id = :id", "id", UserLoginDetailsUtil.getUserLoginDetails().getTenantId()));
            Long total = noticeService.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
            //orderBy.add(CommonOrder.asc("sort"));
            List<TenantNotice> resultList = noticeService.list(query, orderBy, pageNum, pageSize);
            PagerVo<TenantNotice> pager = new PagerVo<TenantNotice>(resultList, total.intValue(), pageNum, pageSize);
            map.put("pager", pager);
        }catch(Exception e){
            logger.error(e);
            throw new GenericException(e);
        }
        return new ModelAndView("saas/sys/notice/allListNotice",map);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> get(
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
            if (StringUtils.isNotBlank(id)) {
                TenantNotice entity = this.noticeService.getByPK(id);
                TenantNoticeVo vo = new TenantNoticeVo();
                BeanUtils.copyProperties(entity, vo);
                map.put("data", vo);
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        } catch (Exception e) {
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }
    @RequestMapping(value = "/add")
    public ModelAndView add() {
        return new ModelAndView("saas/sys/notice/addNotice");
    }

    @RequestMapping(value="/update/{id}",method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") String id)throws GenericException{
        Map<String,Object> map = Maps.newHashMap();
        map.put("id", id);
        return new ModelAndView("saas/sys/notice/updateNotice",map);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(TenantNotice notice) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
            String tenantId = UserLoginDetailsUtil.getUserLoginDetails().getTenantId();
            Tenant tenant = new Tenant();
            tenant.setId(tenantId);
            notice.setTenant(tenant);
            noticeService.save(notice);
            map.put("success", true);
            //map.put("data", notice);
        } catch (Exception e) {
            logger.error(e);
            map.put("success", false);
        }

        return map;
    }

    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody Map<String, Object> delete(
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
            if (StringUtils.isNotBlank(id)) {
                this.noticeService.deleteByPK(id);
                map.put("success", true);
            }
        } catch (Exception e) {
            logger.error(e);
            map.put("success", false);

        }
        return map;
    }

    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> update(TenantNotice notice) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
            TenantNotice entity = this.noticeService.getByPK(notice.getId());
            entity.setNoticeContent(notice.getNoticeContent());
            entity.setNoticeLevel(notice.getNoticeLevel());
            entity.setNoticeTitle(notice.getNoticeTitle());
            entity.setPushWay(notice.getPushWay());

            this.noticeService.update(entity);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "/get/online")
    public @ResponseBody Map<String, Object> onlineNotice() throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
			/*this.noticeService.update(notice);*/
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" pushWay = :pushWay ", "pushWay", Short.valueOf("2")));
            query.add(CommonRestrictions.and(" status = :status ", "status", Boolean.TRUE));
            //Long total = noticeService.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
            //orderBy.add(CommonOrder.asc("sort"));
            List<TenantNotice> resultList = noticeService.list(query, orderBy, 1, 20);

            map.put("success", true);
            if(resultList.size()>0){
                map.put("noticeContent", resultList.get(0).getNoticeContent());
            }
        } catch (Exception e) {
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }
}

