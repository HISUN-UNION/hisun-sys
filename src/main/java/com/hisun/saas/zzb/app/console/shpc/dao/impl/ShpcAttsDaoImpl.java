package com.hisun.saas.zzb.app.console.shpc.dao.impl;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcAttsDao;
import com.hisun.saas.zzb.app.console.shpc.entity.ShpcAtts;
import org.springframework.stereotype.Repository;

/**
 * Created by zhouying on 2017/11/15.
 */
@Repository
public class ShpcAttsDaoImpl extends TenantBaseDaoImpl<ShpcAtts,String> implements ShpcAttsDao {

}
