package com.hisun.saas.zzb.app.console.gbmc.dao.impl;

import com.hisun.base.dao.impl.BaseDaoImpl;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01Dao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Repository
public class GbMcA01DaoImpl extends BaseDaoImpl<GbMcA01,String> implements GbMcA01Dao {


    public void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String b01Id){

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        int maxRow = this.getMaxRowFromWordDataMap(dataMap);

        for (int i = 0; i < maxRow; i++) {
            Map<String, String> rowMap = new HashMap<String, String>();
            //找出每一行的Map
            for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = dataMap.get(key);
                if (i == getRowIndex(key)) {
                    rowMap.put(key, value);
                }
            }
            if (rowMap.size() > 0) {
                list.add(rowMap);
            }
            if (rowMap.size() == 0) {
                break;
            }
        }
        int px = 1;
        for (Map<String, String> map : list) {
            //构造每一行的insert语句
            String idValue = UUIDUtil.getUUID();
            StringBuffer insertSql = new StringBuffer("INSERT INTO ");
            insertSql.append("app_mc_a01");
            insertSql.append(" (");
            //生成字段sql,值sql
            StringBuffer fieldSql = new StringBuffer();
            fieldSql.append(" id,b01_id,a01_px,tenant_id ");
            StringBuffer valueSql = new StringBuffer();


            valueSql.append("(");
            valueSql.append("'").append(idValue).append("'");
            valueSql.append(",'").append(b01Id).append("'");
            valueSql.append(",").append(px).append("");
            valueSql.append(",'").append(tenant.getId()).append("'");

            for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = dataMap.get(key);
                fieldSql.append(",");
                fieldSql.append(WordUtil.getSqlField(key));
                valueSql.append(",");
                valueSql.append("'").append(value).append("'");

            }
            insertSql.append(fieldSql);
            insertSql.append(" )");

            insertSql.append(" VALUES ");
            insertSql.append(valueSql);
            insertSql.append(")");

            List<Object> paramList = new ArrayList<Object>();
            this.executeNativeBulk(insertSql.toString(),paramList);
            px++;
        }
    }



    private int getMaxRowFromWordDataMap(Map<String, String> dataMap) {
        int i = 0;
        String key = "";
        if (dataMap != null) {
            for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
                key = it.next();
                break;
            }
            i = WordUtil.getRowCount(key);
        }
        return i;
    }

    private int getRowIndex(String key) {
        return Integer.valueOf(key.substring(key.lastIndexOf(WordUtil.dot) + 1)).intValue();
    }
}
