package com.hisun.saas.sys.tenant.user.service.impl;

import com.hisun.saas.sys.tenant.role.dao.TenantRoleDao;
import com.hisun.saas.sys.tenant.role.entity.TenantRole;
import com.hisun.saas.sys.tenant.user.dao.TenantUserDao;
import com.hisun.saas.sys.tenant.user.dao.TenantUserRoleDao;
import com.hisun.saas.sys.tenant.user.entity.TenantUser;
import com.hisun.saas.sys.tenant.user.entity.TenantUserRole;
import com.hisun.saas.sys.tenant.user.service.TenantUserRoleService;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>类名称:TenantUserRoleServiceImpl</p>
 * <p>类描述:</p>
 * <p>公司:湖南海数互联信息技术有限公司</p>
 *
 * @创建者:init
 * @创建人:15/11/18下午2:16
 * @创建人联系方式:init@hn-hisun.com
 */
@Service
public class TenantUserRoleServiceImpl extends BaseServiceImpl<TenantUserRole,String> implements TenantUserRoleService {

    private TenantUserRoleDao tenantUserRoleDao;

    @Autowired
    private TenantUserDao tenantUserDao;

    @Autowired
    private TenantRoleDao tenantRoleDao;

    @Autowired
    @Override
    public void setBaseDao(BaseDao<TenantUserRole, String> baseDao) {
        this.baseDao = baseDao;
        this.tenantUserRoleDao = (TenantUserRoleDao)baseDao;
    }

    @Override
    public List<TenantUserRole> getUserRoleByUserId(String userId) {
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" user.id = :userId", "userId", userId));
        List<TenantUserRole> roleList = tenantUserRoleDao.list(query, null);
        return roleList;
    }

    @Override
    public void saveOrUpdate(String userId, String[] roleIds) {
        TenantUser user = this.tenantUserDao.getByPK(userId);
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" user = :user ", "user", user));
        tenantUserRoleDao.deleteBatch(query);
        if (roleIds != null && roleIds.length > 0) {
            TenantUserRole userRole;
            for (String roleId : roleIds) {
                userRole = new TenantUserRole();
                userRole.setUser(user);
                TenantRole role = this.tenantRoleDao.getByPK(roleId);
                userRole.setRole(role);
                this.tenantUserRoleDao.save(userRole);
            }
        }
    }
}
