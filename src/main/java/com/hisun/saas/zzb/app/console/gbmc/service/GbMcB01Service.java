package com.hisun.saas.zzb.app.console.gbmc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;

import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GbMcB01Service extends BaseService<GbMcB01,String> {

    public static String ATTS_PATH = File.separator+"mcb01"+ File.separator;
}
