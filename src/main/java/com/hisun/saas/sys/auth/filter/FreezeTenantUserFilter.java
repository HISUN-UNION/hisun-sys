package com.hisun.saas.sys.auth.filter;

import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.tenant.user.entity.TenantUser;
import com.hisun.saas.sys.tenant.user.service.TenantUserService;
import com.hisun.base.auth.Constants;
import com.hisun.base.auth.service.SessionHelper;
import com.hisun.base.entity.TombstoneEntity;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>类名称：AjaxNoAuthenticationFilter</p>
 * <p>类描述：判断租户用户是否冻结，冻结立即踢掉</p>
 * <p>公司：湖南海数互联信息技术有限公司</p>
 *
 * @创建人：lyk
 * @创建时间：2015/10/15 9:09
 * @创建人联系方式：init@hn-hisun.com
 */
public class FreezeTenantUserFilter extends PathMatchingFilter {

    @Autowired
    private TenantUserService tenantUserService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        if(details == null || details.getType() != Constants.TYPE_TENANT){
            //为空不管,或者不是租户用户
            return true;
        }
        TenantUser tenantUser = tenantUserService.getByPK(details.getUserid());
        if(tenantUser.getTombstone() == TombstoneEntity.TOMBSTONE_TRUE){
            //已被冻结,踢掉SESSION
            sessionHelper.kickOutSession(tenantUser.getUsername());
        }
        return true;
    }
}

