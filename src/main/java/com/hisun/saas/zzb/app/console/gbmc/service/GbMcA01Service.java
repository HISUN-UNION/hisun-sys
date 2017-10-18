package com.hisun.saas.zzb.app.console.gbmc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;

import java.io.File;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GbMcA01Service extends BaseService<GbMcA01,String> {

    public static String ATTS_PATH = File.separator+"gbmc"+File.separator+"mca01"+ File.separator;
    public static String IMPORT_DOC_TEMPLATE = ATTS_PATH +"gbmca01.docx";


    public void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String b01Id);

}
