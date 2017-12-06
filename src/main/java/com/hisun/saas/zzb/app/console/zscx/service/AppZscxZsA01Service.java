package com.hisun.saas.zzb.app.console.zscx.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZsA01;

import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppZscxZsA01Service extends BaseService<AppZscxZsA01,String> {

    Integer getMaxPx(String b01Id) ;
    void updatePx(String b01Id,int oldPx,int newPx);
}
