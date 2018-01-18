package com.hisun.saas.zzb.app.console.aset.service.impl;

import com.aspose.words.*;
import com.google.common.collect.Lists;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.aset.dao.AppAsetA01QueryDao;
import com.hisun.saas.zzb.app.console.aset.dao.AppAsetA02Dao;
import com.hisun.saas.zzb.app.console.aset.dao.AppAsetA36Dao;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01Query;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01QueryService;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA01QueryVo;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA36Vo;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gbrmspb;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.*;
import java.util.List;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppAsetA01QueryServiceImpl extends BaseServiceImpl<AppAsetA01Query,String> implements AppAsetA01QueryService {

    private AppAsetA01QueryDao appAsetA01QueryDao;
    @Resource
    private AppAsetA01Service appAsetA01Service;
    @Resource
    private GbMcService gbMcService;


    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;
    

    @Autowired
    public void setBaseDao(BaseDao<AppAsetA01Query, String> appAsetA01QueryDao) {
        this.baseDao = appAsetA01QueryDao;
        this.appAsetA01QueryDao = (AppAsetA01QueryDao) appAsetA01QueryDao;
    }
    public Integer getMaxPx(){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> arg=new HashMap<String, Object>();
        String hql = "select max(t.query_sort) as px from app_aset_a01_query t where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id)  order by  t.query_sort asc";
        arg.put("tombstone", "0");
        arg.put("tenant_id", userLoginDetails.getTenantId());
        List<Map> maxSorts = this.appAsetA01QueryDao.countReturnMapBySql(hql, arg);
        Integer maxPx = (Integer) maxSorts.get(0).get("px");
        return maxPx;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        String sql = "UPDATE app_aset_a01_query t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.query_sort=t.query_sort-1";
        } else {
            sql = sql + "t.query_sort=t.query_sort+1";
        }

        sql = sql + " where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) ";
        if(newPx > oldPx) {
            sql = sql + " and t.query_sort<=" + newPx + " and t.query_sort >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.query_sort<" + oldPx + " and t.query_sort>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("tombstone", "0");
        paramMap.put("tenant_id", userLoginDetails.getTenantId());
        this.appAsetA01QueryDao.update(sql, paramMap);
    }



    public void saveAsGbmc(AppAsetA01Query query)throws Exception{
        //根据Query创建Gbmc
        GbMc gbMc = new GbMc();
        gbMc.setMc(query.getQueryName());
        gbMc.setPx(99);
        gbMc.setIsMl(GbMc.WML);//默认未无目录

        List<Object> paramList = Lists.newArrayList();
        String hql = " from AppAsetA01 a01  inner join a01.appAsetA02s a02  inner join a02.appBsetB01 b01  " +
                "inner join b01.appBsetFl2B01s fltob01  where a01.tombstone =? ";
        if(StringUtils.isEmpty(query.getQueryCondition())==false){
            hql+=query.getQueryCondition();
        }
        String orderBy =  "  order by fltob01.px,b01.px,a02.jtlPx ";
        paramList.add(0);
        int total = this.appAsetA01Service.count("select  count(distinct a01.id) " + hql, paramList);
        int dealCount = total/200;
        for(int i=1;i<=dealCount+1;i++) {
            List<AppAsetA01> appAsetA01s = this.appAsetA01Service.list("select  DISTINCT(a01) " + hql+orderBy, paramList,i,200);
            for(AppAsetA01 appAsetA01 : appAsetA01s){
                GbMcA01 gbMcA01 = new GbMcA01();
                BeanUtils.copyProperties(gbMcA01,appAsetA01);
                gbMcA01.setId(null);

                GbMcA01gbrmspb gbMcA01gbrmspb = new GbMcA01gbrmspb();
                BeanUtils.copyProperties(gbMcA01gbrmspb,appAsetA01);
                gbMcA01gbrmspb.setId(null);
                gbMcA01.addGbrmspb(gbMcA01gbrmspb);
                gbMc.addGbMcA01(gbMcA01);
            }
        }

     this.gbMcService.save(gbMc);
    }
    
}
