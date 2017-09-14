package com.hisun.saas.sys.admin.role.entity;

import com.hisun.saas.sys.admin.resource.entity.Resource;
import com.hisun.base.entity.AbstractRole;
import com.hisun.saas.sys.admin.user.entity.UserRole;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 
 *<p>类名称：PlatformRole</p>
 *<p>类描述: 平台角色</p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：Jason
 *@创建时间：2015-11-18 上午11:40:40
 *@创建人联系方式：jason4j@qq.com
 *@version v0.1
 */

//消除未生成序列ID的警告
@SuppressWarnings("serial")
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_ROLE")

public class Role extends AbstractRole implements Serializable {

	@OneToMany(mappedBy="role",fetch=FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	private List<UserRole> userRoles;//用户拥有的角色(中间表)

	@ManyToMany(targetEntity=Resource.class,fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="SYS_ROLE_RESOURCE",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="resource_id")})
	@OrderBy(value="sort")
	private List<Resource> resources;//角色和资源多对多

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}