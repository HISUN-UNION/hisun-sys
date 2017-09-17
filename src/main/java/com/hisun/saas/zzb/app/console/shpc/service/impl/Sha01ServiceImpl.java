package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01Dao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01ServiceImpl extends BaseServiceImpl<Sha01,String> implements Sha01Service {

    private Sha01Dao sha01Dao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01, String> sha01Dao) {
        this.baseDao = sha01Dao;
        this.sha01Dao = (Sha01Dao) sha01Dao;
    }


    public void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String pcId){
        this.sha01Dao.saveFromWordDataMap(tenant,dataMap,pcId);
    }

}