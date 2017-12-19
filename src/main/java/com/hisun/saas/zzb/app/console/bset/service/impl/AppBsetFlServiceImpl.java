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
        String sql = " select * from sm_unitTreeNode  where sm_unitTreeNode.parentNodeId ='0' ";
        List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(), (Object[]) null);

        String sql2 = " select * from sm_unitTreeNode  where sm_unitTreeNode.parentNodeId in (select nodeId from sm_unitTreeNode) ";
        List<Map<String, Object>> list2 = queryRunner.query(conn, sql2, new MapListHandler(), (Object[]) null);
        list.addAll(list2);
        for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext(); ) {
            Map<String, Object> m = li.next();

            StringBuffer fields = new StringBuffer();
            fields.append("insert into app_bset_fl (");
            fields.append(" tombstone ");
            StringBuffer values = new StringBuffer();
            values.append(") values (");
            values.append(" 0 ");

            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext(); ) {
                Map.Entry<String, Object> e = mi.next();
                String key = e.getKey();
                Object value = e.getValue() == null ? "" : e.getValue();
                if (key.equalsIgnoreCase("nodeID")) {
                    fields.append(",id");
                    values.append(",'" + value + "'");
                } else if (key.equalsIgnoreCase("parentNodeID")) {
                    //特殊处理顶层节点
                    if (value.toString() == null || value.toString().equals("0")) {
                    } else {
                        fields.append(",parent_id");
                        values.append(",'" + value + "'");
                    }

                } else if (key.equalsIgnoreCase("nodeName")) {
                    fields.append(",fl");
                    values.append(",'" + value + "'");
                } else if (key.equalsIgnoreCase("sortID")) {
                    fields.append(",px");
                    values.append(",'" + value + "'");
                }

            }
            values.append(")");
            List<Object> paramList = new ArrayList<Object>();
            this.appBsetFlDao.executeNativeBulk(fields.append(values).toString(), paramList);
            order++;
        }

        DbUtils.close(conn);
        return order;
    }

}
