package com.hisun.saas.zzb.app.console.shpc.dao.imp;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcDao;
import com.hisun.saas.zzb.app.console.shpc.dao.ShtpDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.entity.Shtp;
import org.springframework.stereotype.Repository;

/**
 * Created by zhouying on 2017/9/15.
 */
@Repository
public class ShtpDaoImpl extends TenantBaseDaoImpl<Shtp, String> implements ShtpDao {

}
