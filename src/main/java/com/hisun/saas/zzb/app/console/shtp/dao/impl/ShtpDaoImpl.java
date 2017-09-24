package com.hisun.saas.zzb.app.console.shtp.dao.impl;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.app.console.shtp.dao.ShtpDao;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtp;
import org.springframework.stereotype.Repository;

/**
 * Created by zhouying on 2017/9/15.
 */
@Repository
public class ShtpDaoImpl extends TenantBaseDaoImpl<Shtp, String> implements ShtpDao {

}
