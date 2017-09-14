package com.hisun.saas.zzb.app.console.shpc.dao.imp;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01gbrmspbDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by zhouying on 2017/9/12.
 */
@Repository
public class Sha01gbrmspbDaoImpl extends TenantBaseDaoImpl<Sha01gbrmspb, String> implements Sha01gbrmspbDao {

    public void saveFromWordDataMap(Tenant tenant ,Map<String, String> dataMap,String a01pk) {

        String idValue = UUIDUtil.getUUID();
        StringBuffer insertSql = new StringBuffer("INSERT INTO ");
        insertSql.append("APP_SH_A01_GBRMSPB");
        insertSql.append(" (");
        //生成字段sql,值sql
        StringBuffer fieldSql = new StringBuffer();
        fieldSql.append("ID,APP_SH_A01_ID");
        StringBuffer valueSql = new StringBuffer();

        valueSql.append("(");
        valueSql.append("'").append(idValue).append("'");
        valueSql.append(",'").append(a01pk).append("'");
        Map<String,String> listDataMap = new HashMap<String,String>();
        for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = dataMap.get(key);
            //组合主表以"["或"#image"开头的
            if(key.startsWith(WordUtil.dataPrefix)||key.startsWith(WordUtil.imageSign)) {
                fieldSql.append(",");
                fieldSql.append(WordUtil.getSqlField(key));
                valueSql.append(",");
                valueSql.append("'").append(value).append("'");
            }else if(key.startsWith(WordUtil.listSign)){
                listDataMap.put(key,value);
            }
        }
        insertSql.append(fieldSql);
        insertSql.append(" )");

        insertSql.append(" VALUES ");
        insertSql.append(valueSql);
        insertSql.append(")");

        this.saveSha01gzjlFromWordDataMap(tenant,listDataMap,idValue);
    }


    public void saveSha01gzjlFromWordDataMap(Tenant tenant ,Map<String,String> dataMap,String a01Pk){
        for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = dataMap.get(key);
            if(key.toUpperCase().indexOf("APP_SH_A01_GZJL")>0){
                List<String> list = Arrays.asList(value.split("\n"));
                int i =0;
                for(String str:list){
                    i++;
                    StringBuffer insertSql = new StringBuffer("INSERT INTO ");
                    insertSql.append("APP_SH_A01_GZJL");
                    insertSql.append(" (ID,APP_SH_A01_ID,JLSM)");
                    insertSql.append(" VALUES ");
                    insertSql.append("(");
                    insertSql.append("'").append(UUIDUtil.getUUID()).append("',");
                    insertSql.append("'").append(str).append("',");
                    insertSql.append("'").append(a01Pk).append("',");
                    insertSql.append(""+i);
                    insertSql.append(")");
                }
            }
        }
    }



    public void saveSha01shgxFromWordDataMap(Map<String,String> dataMap){

    }


}
