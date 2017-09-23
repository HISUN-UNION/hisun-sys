package com.hisun.saas.zzb.app.console.gendata.dao.impl;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.app.console.gendata.dao.GendataDao;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;
import org.springframework.stereotype.Repository;

/**
 * Created by zhouying on 2017/9/23.
 */
@Repository
public class GendataImpl extends TenantBaseDaoImpl<Gendata,String> implements GendataDao {

}
