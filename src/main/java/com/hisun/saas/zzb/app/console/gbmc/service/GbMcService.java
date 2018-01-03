package com.hisun.saas.zzb.app.console.gbmc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GbMcService extends BaseService<GbMc,String> {

    String ATTS_PATH = File.separator+"gbmc"+ File.separator;
    String APP_ATTS_PATH = "gbmc/";
    int getA01Count(String id) throws Exception;
    void saveFromWordDataMap(GbMc gbMc,List<Map<String, String>> dataList);
    String toSqliteInsertSql(GbMc entity);
    void saveAsSqlite(String id,String sqlite) throws Exception;
}
