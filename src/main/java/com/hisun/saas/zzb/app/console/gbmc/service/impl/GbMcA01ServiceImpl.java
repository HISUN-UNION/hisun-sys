package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01Dao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbMcA01ServiceImpl extends BaseServiceImpl<GbMcA01,String> implements GbMcA01Service {

    private GbMcA01Dao gbMcA01Dao;

    @Autowired
    public void setBaseDao(BaseDao<GbMcA01, String> gbMcA01Dao) {
        this.baseDao = gbMcA01Dao;
        this.gbMcA01Dao = (GbMcA01Dao) gbMcA01Dao;
    }


    public void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String b01Id){
        this.gbMcA01Dao.saveFromWordDataMap(tenant,dataMap,b01Id);
    }

}
