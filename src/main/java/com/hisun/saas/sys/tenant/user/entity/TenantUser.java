package com.hisun.saas.sys.tenant.user.entity;

import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.base.entity.AbstractUser;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 *<p>类名称：User</p>
 *<p>类描述: </p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：qinjw
 *@创建时间：2015年3月18日 下午5:01:56
 *@创建人联系方式：qinjw@30wish.net
 *@version
 */
@Entity
@Table(name="SYS_TENANT_USER")
public class TenantUser extends AbstractUser implements Serializable {

	public static int STATUS_NO_ACTIVATION = 0;//未激活
	public static int STATUS_ACTIVATION = 1;//激活

	@ManyToOne(targetEntity = Tenant.class)
	@JoinColumn(name="tenant_id")
	private Tenant tenant;

	@Column(name="status")
	private int status;//状态

	public TenantUser(){}

	public TenantUser(String id){
		setId(id);
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getExistName(){
		return StringUtils.isBlank(getRealname())?getUsername():getRealname();
	}
}