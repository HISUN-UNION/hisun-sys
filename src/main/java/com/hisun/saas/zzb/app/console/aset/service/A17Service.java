package com.hisun.saas.zzb.app.console.aset.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.aset.entity.A17;

import javax.sql.DataSource;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface A17Service extends BaseService<A17,String> {

    int saveFromYw(DataSource dataSource)throws Exception;
    int saveFromZdwx(DataSource dataSource)throws Exception;
}
