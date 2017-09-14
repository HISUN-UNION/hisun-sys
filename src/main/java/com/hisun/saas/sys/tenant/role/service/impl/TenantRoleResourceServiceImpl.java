package com.hisun.saas.sys.tenant.role.service.impl;

import com.hisun.saas.sys.tenant.role.dao.TenantRoleResourceDao;
import com.hisun.saas.sys.tenant.role.entity.TenantRoleResource;
import com.hisun.saas.sys.tenant.role.service.TenantRoleResourceService;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>类名称:TenantRoleResourceServiceImpl</p>
 * <p>类描述:</p>
 * <p>公司:湖南海数互联信息技术有限公司</p>
 *
 * @创建者:init
 * @创建人:15/11/18下午2:11
 * @创建人联系方式:init@hn-hisun.com
 */
@Service
public class TenantRoleResourceServiceImpl extends BaseServiceImpl<TenantRoleResource,String> implements TenantRoleResourceService {

    private TenantRoleResourceDao tenantRoleResourceDao;

    @Autowired
    @Override
    public void setBaseDao(BaseDao<TenantRoleResource, String> baseDao) {
        this.baseDao = baseDao;
        this.tenantRoleResourceDao = (TenantRoleResourceDao)baseDao;
    }
}
