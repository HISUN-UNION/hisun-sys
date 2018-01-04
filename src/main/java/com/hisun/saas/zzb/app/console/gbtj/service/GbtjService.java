package com.hisun.saas.zzb.app.console.gbtj.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;

import java.sql.SQLException;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GbtjService extends BaseService<Gbtj,String> {

    String toSqliteInsertSql(Gbtj gbtj);
    void saveAsSqlite(String gbtjId, String sqlite,String imgdir,String attsdir)throws ClassNotFoundException,SQLException;
}
