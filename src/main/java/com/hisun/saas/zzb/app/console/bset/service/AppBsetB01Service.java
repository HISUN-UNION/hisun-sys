package com.hisun.saas.zzb.app.console.bset.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;

import javax.sql.DataSource;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppBsetB01Service extends BaseService<AppBsetB01,String> {

    Integer getMaxPx(String parentId) ;
    void updatePx(String parentId,int oldPx,int newPx);
    int saveBsetB01FromYw(DataSource dataSource)throws Exception;
}
