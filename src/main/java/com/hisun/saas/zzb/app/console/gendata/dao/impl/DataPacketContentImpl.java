package com.hisun.saas.zzb.app.console.gendata.dao.impl;

import com.hisun.saas.sys.tenant.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.app.console.gendata.dao.DataPacketContentDao;
import com.hisun.saas.zzb.app.console.gendata.dao.GendataDao;
import com.hisun.saas.zzb.app.console.gendata.entity.DataPacketContent;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;
import org.springframework.stereotype.Repository;

/**
 * Created by zhouying on 2017/9/23.
 */
@Repository
public class DataPacketContentImpl extends TenantBaseDaoImpl<DataPacketContent,String> implements DataPacketContentDao {

}
