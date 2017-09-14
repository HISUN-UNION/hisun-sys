package com.hisun.saas.sys.tenant.tenant.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 
 *<p>类名称：TenantEntity</p>
 *<p>类描述: 用于标示当前实体是租户实体，对于租户实体需要对进行数据逻辑隔离。</p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：zhouying
 *@创建时间：2015年5月22日 下午5:58:45
 *@创建人联系方式：zhouying@30wish.net
 *@version
 */
@MappedSuperclass
@FilterDef(name="tenantFilter",parameters=@ParamDef(name="tenantFilterParam",type="string"))
@Filters({
		@Filter(name="tenantFilter",condition=" tenant_id=:tenantFilterParam")
})
public class TenantEntity extends TombstoneEntity {

	@ManyToOne(targetEntity = Tenant.class,fetch = FetchType.LAZY)
	@JoinColumn(name="tenant_id")
	protected Tenant tenant;

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
}
