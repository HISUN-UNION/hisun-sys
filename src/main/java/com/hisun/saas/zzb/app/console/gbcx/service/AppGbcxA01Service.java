package com.hisun.saas.zzb.app.console.gbcx.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.gbcx.entity.AppGbcxA01;
import com.hisun.saas.zzb.app.console.gbcx.entity.AppGbcxB01;

import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppGbcxA01Service extends BaseService<AppGbcxA01,String> {
    public static String ATTS_PATH = File.separator+"gbcx"+ File.separator;
    Integer getMaxPx(String b01Id) ;
    void updatePx(String b01Id,int oldPx,int newPx);
}
