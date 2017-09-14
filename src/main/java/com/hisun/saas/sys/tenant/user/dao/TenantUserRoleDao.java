package com.hisun.saas.sys.tenant.user.dao;

import com.hisun.saas.sys.tenant.user.entity.TenantUserRole;
import com.hisun.saas.sys.tenant.role.entity.TenantRole;
import com.hisun.base.dao.BaseDao;

import java.util.List;

/**
 * Created by liyikai on 15/11/18.
 */
public interface TenantUserRoleDao extends BaseDao<TenantUserRole,String> {

    public List<TenantRole> listByUserId(String userId);

    /**
     * 根据用户ID查询用户拥有的角色
     * @param userId
     * @return
     */
    List<TenantUserRole> getUserRoleByUserId(String userId);
}
