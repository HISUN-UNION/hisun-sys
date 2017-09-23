package com.hisun.saas.zzb.app.console.shpc.dao.impl;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import org.springframework.stereotype.Repository;

/**
 * Created by zhouying on 2017/9/15.
 */
@Repository
public class ShpcDaoImpl extends TenantBaseDaoImpl<Shpc, String> implements ShpcDao {

}
