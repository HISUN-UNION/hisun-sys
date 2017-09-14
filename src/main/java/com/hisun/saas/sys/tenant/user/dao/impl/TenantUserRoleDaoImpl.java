package com.hisun.saas.sys.tenant.user.dao.impl;

import com.hisun.saas.sys.tenant.role.entity.TenantRole;
import com.hisun.saas.sys.tenant.user.dao.TenantUserRoleDao;
import com.hisun.saas.sys.tenant.user.entity.TenantUserRole;
import com.hisun.base.dao.impl.BaseDaoImpl;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>类名称:TenantUserRoleDaoImpl</p>
 * <p>类描述:</p>
 * <p>公司:湖南海数互联信息技术有限公司</p>
 *
 * @创建者:init
 * @创建人:15/11/18下午2:04
 * @创建人联系方式:init@hn-hisun.com
 */
@Repository
public class TenantUserRoleDaoImpl extends BaseDaoImpl<TenantUserRole,String> implements TenantUserRoleDao {
    @Override
    public List<TenantRole> listByUserId(String userId) {
        String sql = "select r.* from sys_tenant_user_role ur ,sys_tenant_role r"
                + " where ur.tenant_user_id = :userId and ur.tenant_role_id = r.id";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userId", userId);
        return nativeList(TenantRole.class, sql, paramMap);
    }

    @Override
    public List<TenantUserRole> getUserRoleByUserId(String userId) {
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" user.id = :userId", "userId", userId));
        List<TenantUserRole> roleList = list(query, null);
        return roleList;
    }
}
