package com.hisun.saas.zzb.app.console.bset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFlDao;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFlService;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.ConnectionEvent;
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
public class AppBsetFlServiceImpl extends BaseServiceImpl<AppBsetFl,String> implements AppBsetFlService {

    private AppBsetFlDao appBsetFlDao;

    @Autowired
    public void setBaseDao(BaseDao<AppBsetFl, String> appBsetFlDao) {
        this.baseDao = appBsetFlDao;
        this.appBsetFlDao = (AppBsetFlDao) appBsetFlDao;
    }


    public int saveBsetFlFromYw(DataSource dataSource)throws Exception{

        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from sm_unitTreeNode " , new MapListHandler(),(Object[]) null);
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
            String sql = "select top 400 * from sm_unitTreeNode  where sm_unitTreeNode.nodeId not in"
                    +"(select top "+num+" sm_unitTreeNode.nodeID from sm_unitTreeNode " +
                    "order by sm_unitTreeNode.parentNodeID,sm_unitTreeNode.sortID)"
                    +"order by   sm_unitTreeNode.parentNodeID,sm_unitTreeNode.sortID ";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();

                StringBuffer fields = new StringBuffer();
                fields.append("insert into app_bset_fl (");
                fields.append(" tombstone ");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");

                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if(key.equalsIgnoreCase("nodeID")){
                        fields.append(",id");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("parentNodeID")){
                        fields.append(",parent_id");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("nodeName")){
                        fields.append(",fl");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("sortID")){
                        fields.append(",px");
                        values.append(",'"+value+"'");
                    }

                }
                values.append(")");
                List<Object> paramList = new ArrayList<Object>();
                this.appBsetFlDao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;

            }
        }

        DbUtils.close(conn);
        return order;
    }

}
