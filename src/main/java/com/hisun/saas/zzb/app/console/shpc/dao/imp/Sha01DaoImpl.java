package com.hisun.saas.zzb.app.console.shpc.dao.imp;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01Dao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by zhouying on 2017/9/13.
 */
@Repository
public class Sha01DaoImpl extends TenantBaseDaoImpl<Sha01, String> implements Sha01Dao {

    public void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String pcId) {

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

        for (Map<String, String> map : list) {
            //构造每一行的insert语句
            String idValue = UUIDUtil.getUUID();
            StringBuffer insertSql = new StringBuffer("INSERT INTO ");
            insertSql.append("APP_SH_A01");
            insertSql.append(" (");
            //生成字段sql,值sql
            StringBuffer fieldSql = new StringBuffer();
            fieldSql.append(" ID,APP_SH_PC_ID ");
            StringBuffer valueSql = new StringBuffer();


            valueSql.append("(");
            valueSql.append("'").append(idValue).append("'");
            valueSql.append(",'").append(pcId).append("'");


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

            System.out.println(insertSql);
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



    public static void main (String[] args) throws Exception{

        String wordPath = "/Users/zhouying/Desktop/zzb-app-android/湘西州干部调整配备建议方案.docx";
        String wordPathTemplate = "/Users/zhouying/Desktop/zzb-app-android/template1.docx";

        Map<String,String> dataMap =WordUtil.newInstance().convertMapByTemplate(wordPath,wordPathTemplate,
                "/Users/zhouying/Desktop/zzb-app-android/");



        Sha01DaoImpl impl = new Sha01DaoImpl();
        impl.saveFromWordDataMap(null,dataMap,"1");

    }


}