package com.hisun.saas.zzb.app.console.zscx.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbcx.entity.AppGbcxB01;
import com.hisun.saas.zzb.app.console.zscx.dao.AppZscxB01Dao;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxB01;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxB01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppZscxB01ServiceImpl extends BaseServiceImpl<AppZscxB01,String> implements AppZscxB01Service {

    private AppZscxB01Dao appZscxB01Dao;

    @Autowired
    public void setBaseDao(BaseDao<AppZscxB01, String> appZscxB01Dao) {
        this.baseDao = appZscxB01Dao;
        this.appZscxB01Dao = (AppZscxB01Dao) appZscxB01Dao;
    }
    public Integer getMaxPx(String parentId){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> arg=new HashMap<String, Object>();
        String hql = "select max(t.px) as px from app_zscx_b01 t where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) ";
        if(parentId!=null && !parentId.equals("1")) {
            hql = hql+ " and t.parent_id=(:parentId)";
        }else{
            hql = hql+ " and t.parent_id is null";
        }
        hql = hql+ " order by  t.px asc";
        arg.put("tombstone", "0");
        arg.put("tenant_id", userLoginDetails.getTenantId());
        if(parentId!=null && !parentId.equals("1")) {
            arg.put("parentId", parentId);
        }
        List<Map> maxSorts = this.appZscxB01Dao.countReturnMapBySql(hql, arg);
        Integer maxPx = (Integer) maxSorts.get(0).get("px");
        return maxPx;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(String parentId,int oldPx,int newPx){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        String sql = "UPDATE app_zscx_b01 t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.px=t.px-1";
        } else {
            sql = sql + "t.px=t.px+1";
        }

        sql = sql + " where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) " ;
        if(parentId!=null && !parentId.equals("")) {
            sql = sql+ " and t.parent_id=(:parentId)";
        }else{
            sql = sql+ " and t.parent_id is null";
        }
        if(newPx > oldPx) {
            sql = sql + " and t.px<=" + newPx + " and t.px >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.px<" + oldPx + " and t.px>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("tombstone", "0");
        if(parentId!=null && !parentId.equals("")) {
            paramMap.put("parentId", parentId);
        }
        paramMap.put("tenant_id", userLoginDetails.getTenantId());
        this.appZscxB01Dao.update(sql, paramMap);
    }
}
