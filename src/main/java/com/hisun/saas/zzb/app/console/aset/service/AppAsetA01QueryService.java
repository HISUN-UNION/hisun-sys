package com.hisun.saas.zzb.app.console.aset.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01Query;

import javax.sql.DataSource;
import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppAsetA01QueryService extends BaseService<AppAsetA01Query,String> {



    Integer getMaxPx() ;
    void updatePx( int oldPx, int newPx);
    void saveAsGbmc(AppAsetA01Query query)throws Exception;
}
