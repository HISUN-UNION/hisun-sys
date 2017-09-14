package com.hisun.saas.sys.tenant.dao.imp;

import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.base.dao.impl.BaseDaoImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import org.apache.log4j.Logger;
import org.apache.shiro.ShiroException;
import org.hibernate.Session;

/**
 * <p> Title : TenantBaseDaoImpl</p>
 * <p> Description: 租戶DAO實現類</p>
 * <p> Copyright: Copyright (c) 2016</p>
 * <p> Company: 湖南海数互联信息技术有限公司</p>
 *
 * @author Jason
 * @email jason4j@qq.com
 * @date 2016年03月16 11:45
 */
public class TenantBaseDaoImpl<E extends java.io.Serializable, PK extends java.io.Serializable> extends BaseDaoImpl<E,PK>{

    private static final Logger logger = Logger.getLogger(TenantBaseDaoImpl.class);

    @Override
    public Session getSession() {
        Session session = super.getSession();
    	//如果为租户实体，则默认加上租户过滤条件进行数据逻辑隔离
    	if(TenantEntity.class.isAssignableFrom(this.entityClass)){
            UserLoginDetails userLoginDetails = null;
    		try{
    			//通常情况下可取得当前用户信息，因为用户操作都需要通过认证后才能访问资源。
    			//但是后台轮循程序则可能无法取得当前用户信息，会抛出异常，
    			//事实上后台轮循程序不需要通过用户信息来确保数据隔离。
    			userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
    		}catch(ShiroException e){
    			logger.info("This entity is TenantEntity,but tenantFilter is not used.Reason is ["+e.getMessage()+"]");
    		}
            //admin管理员没有tenant
    		if(userLoginDetails!=null && !"admin".equalsIgnoreCase(userLoginDetails.getUsername())){
    			session.enableFilter("tenantFilter").setParameter("tenantFilterParam", userLoginDetails.getTenantId());
    		}
    	}
        return session;
    }
}
