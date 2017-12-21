package com.hisun.saas.zzb.app.console.bset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFl2B01Dao;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl2B01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFl2B01Service;
import com.hisun.util.UUIDUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppBsetFl2B01ServiceImpl extends BaseServiceImpl<AppBsetFl2B01,String> implements AppBsetFl2B01Service {

    private AppBsetFl2B01Dao appBsetFl2B01Dao;

    @Autowired
    public void setBaseDao(BaseDao<AppBsetFl2B01, String> appBsetFl2B01Dao) {
        this.baseDao = appBsetFl2B01Dao;
        this.appBsetFl2B01Dao = (AppBsetFl2B01Dao) appBsetFl2B01Dao;
    }



    public int saveBsetFl2B01FromYw(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select top 600 * from sm_unitCatalog ";
        List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();

            StringBuffer fields = new StringBuffer();
            fields.append("insert into app_bset_fl_2_b01 (");
            fields.append(" tombstone,id,tenant_id,create_user_id,create_user_name ");
            StringBuffer values = new StringBuffer();
            values.append(") values (");
            values.append(" 0,'"+ UUIDUtil.getUUID()+"'");
            values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                    .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                    .append(",'").append(userLoginDetails.getUsername()).append("'");

            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                String key = e.getKey();
                Object value = e.getValue()==null?"":e.getValue();
                if(key.equalsIgnoreCase("nodeID")){
                    fields.append(",fl_id");
                    values.append(",'"+value+"'");
                }else if(key.equalsIgnoreCase("unitCodeNo")){
                    fields.append(",b01_id");
                    values.append(",'"+value+"'");
                }else if(key.equalsIgnoreCase("sortID")){
                    fields.append(",px");
                    values.append(",'"+value+"'");
                }

            }
            values.append(")");

            List<Object> paramList = new ArrayList<Object>();
            this.appBsetFl2B01Dao.executeNativeBulk(fields.append(values).toString(),paramList);
            order++;

        }

        DbUtils.close(conn);
        return order;
    }

}
