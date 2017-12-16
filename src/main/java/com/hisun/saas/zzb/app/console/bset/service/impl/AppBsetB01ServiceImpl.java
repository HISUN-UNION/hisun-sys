package com.hisun.saas.zzb.app.console.bset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetB01Dao;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppBsetB01ServiceImpl extends BaseServiceImpl<AppBsetB01,String> implements AppBsetB01Service {

    private AppBsetB01Dao appBsetB01Dao;

    @Autowired
    public void setBaseDao(BaseDao<AppBsetB01, String> appBsetB01Dao) {
        this.baseDao = appBsetB01Dao;
        this.appBsetB01Dao = (AppBsetB01Dao) appBsetB01Dao;
    }
    public Integer getMaxPx(String parentId){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> arg=new HashMap<String, Object>();
        String hql = "select max(t.px) as px from app_gbcx_b01 t where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) ";
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
        List<Map> maxSorts = this.appBsetB01Dao.countReturnMapBySql(hql, arg);
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
        String sql = "UPDATE app_gbcx_b01 t SET ";
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
        this.appBsetB01Dao.update(sql, paramMap);
    }


    public int saveBsetB01FromYw(DataSource dataSource)throws Exception{

        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from b000  where b000.B000_B0111 is not null " , new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = countList.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                Object value = e.getValue();
                count = ((Integer)value).intValue();
            }
        }
        //每次处理400条
        int dealCount = count/400;
        for(int i=0;i<=dealCount;i++){
            int num = i*400;
            String sql = "select top 400 * from b000  where b000.B000_B0111 is not null and b000.B000_B0111 not in"
                    +"(select top "+num+" b000.B000_B0111 from b000)"
                    +"order by  case when b000.B000_DWSX is null then 1 else 0 end ,b000.B000_DWSX ";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();
                fields.append("insert into app_bset_b01 (");
                fields.append(" tombstone ");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");

                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if(key.equalsIgnoreCase("B000_B0111")){
                        fields.append(",id");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("B000_B0101A")){
                        fields.append(",b0101");
                        values.append(",'"+value+"'");
                    }
                 }
                fields.append(",px");
                values.append(","+order+"");
                values.append(")");
                List<Object> paramList = new ArrayList<Object>();
                this.appBsetB01Dao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;

            }
        }

        DbUtils.close(conn);
        return order;
    }



}
