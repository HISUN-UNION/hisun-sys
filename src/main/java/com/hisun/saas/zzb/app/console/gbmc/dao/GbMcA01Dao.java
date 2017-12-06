package com.hisun.saas.zzb.app.console.gbmc.dao;

import com.hisun.base.dao.BaseDao;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GbMcA01Dao extends BaseDao<GbMcA01,String> {


    public void saveFromWordDataMap( Map<String, String> dataMap, GbMcB01 gbMcB01);

}
