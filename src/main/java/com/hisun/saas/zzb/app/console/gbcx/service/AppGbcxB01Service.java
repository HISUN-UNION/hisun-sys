package com.hisun.saas.zzb.app.console.gbcx.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.appclient.entity.AppClient;
import com.hisun.saas.zzb.app.console.gbcx.entity.AppGbcxB01;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppGbcxB01Service extends BaseService<AppGbcxB01,String> {
    Integer getMaxPx(String parentId) ;
    void updatePx(String parentId,int oldPx,int newPx);
}
