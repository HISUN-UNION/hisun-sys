package com.hisun.saas.zzb.app.console.gbmc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GbMcService extends BaseService<GbMc,String> {

    int getA01Count(String id) throws Exception;
    void saveFromWordDataMap(GbMc gbMc, int isMl,List<Map<String, String>> dataList);
    GbMcB01 saveWMLB01(GbMc gbMc);
}
