package com.hisun.saas.zzb.app.console.shpc.dao.imp;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01gbrmspbDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
import org.springframework.stereotype.Repository;

import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * Created by zhouying on 2017/9/12.
 */
@Repository
public class Sha01gbrmspbDaoImpl extends TenantBaseDaoImpl<Sha01gbrmspb, String> implements Sha01gbrmspbDao {

    public String saveFromWord(Sha01gbrmspb gbrmspb , Map<String, String> wordDataMap) {
        String idValue = UUIDUtil.getUUID();
        StringBuffer insertSql = new StringBuffer("INSERT INTO ");
        insertSql.append("APP_SH_A01_GBRMSPB");
        insertSql.append(" (");
        //生成字段sql,值sql
        StringBuffer fieldSql = new StringBuffer();
        fieldSql.append("ID,APP_SH_A01_ID,FILE2IMG_PATH,FILE_PATH");
        StringBuffer valueSql = new StringBuffer();

        valueSql.append("(");
        valueSql.append("'").append(idValue).append("'");
        valueSql.append(",'").append(gbrmspb.getSha01().getId()).append("'");
        valueSql.append(",'").append(gbrmspb.getFile2imgPath()).append("'");
        valueSql.append(",'").append(gbrmspb.getFilepath()).append("'");

        Map<String,String> listDataMap = new HashMap<String,String>();
        for (Iterator<String> it = wordDataMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = wordDataMap.get(key);
            //组合主表以"["或"#image"开头的
            if(key.startsWith(WordUtil.dataPrefix)) {
                fieldSql.append(",");
                fieldSql.append(WordUtil.getSqlField(key));
                valueSql.append(",");
                valueSql.append("'").append(value).append("'");
            }
        }
        insertSql.append(fieldSql);
        insertSql.append(" )");

        insertSql.append(" VALUES ");
        insertSql.append(valueSql);
        insertSql.append(")");

        List<Object> paramList = new ArrayList<Object>();
        this.executeNativeBulk(insertSql.toString(),paramList);

        return idValue;

    }

}
