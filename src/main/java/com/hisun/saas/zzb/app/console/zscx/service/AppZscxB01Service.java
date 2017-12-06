package com.hisun.saas.zzb.app.console.zscx.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.gbcx.entity.AppGbcxB01;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxB01;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppZscxB01Service extends BaseService<AppZscxB01,String> {
    Integer getMaxPx(String parentId) ;
    void updatePx(String parentId,int oldPx,int newPx);
}
