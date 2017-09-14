package com.hisun.saas.sys.tenant.role.dao;

import com.hisun.saas.sys.tenant.role.entity.TenantRole;
import com.hisun.base.dao.BaseDao;

/**
 * Created by liyikai on 15/11/18.
 */
public interface TenantRoleDao extends BaseDao<TenantRole,String>{

    public void batchSaveResource(final String roleId, final String[] resourceIds);

    /**
     * 根据CODE获取
     * @param code
     * @return
     */
    public TenantRole getByCode(String code);
}
