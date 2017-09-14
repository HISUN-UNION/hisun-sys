package com.hisun.saas.zzb.app.console.shpc.dao.imp;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01Dao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.util.WordUtil;

import java.util.*;

/**
 * Created by zhouying on 2017/9/13.
 */
public class Sha01DaoImpl extends TenantBaseDaoImpl<Sha01,String> implements Sha01Dao {

    public void saveFromWordDataMap(Tenant tenant , Map<String, String> dataMap, String a01pk) {

        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        int maxRow = this.getMaxRowFromWordDataMap(dataMap);

        for(int i=0; i<maxRow;i++){
            Map<String,String> rowMap = new HashMap<String,String>();
            //找出每一行的Map
            for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = dataMap.get(key);
                if (i==getRowIndex(key)){
                    rowMap.put(key,value);
                }
            }
            if(rowMap.size()>0){
                list.add(rowMap);
            }
            if(rowMap.size()==0){
                break;
            }
        }




    }


    private int getMaxRowFromWordDataMap(Map<String, String> dataMap){
        int i =0;
        String key = "";
        if(dataMap!=null){
            for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
                 key = it.next();
                break;
            }
            i = WordUtil.getRowCount(key);
        }
        return i;
    }

    private int getRowIndex(String key){
        return Integer.valueOf(key.substring(key.indexOf("_")+1)).intValue();
    }
}
