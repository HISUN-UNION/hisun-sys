package com.hisun.saas.sys.admin.resource.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 *<p>类名称：PlatformUser</p>
 *<p>类描述: </p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：Jason
 *@创建时间：2015-3-18 15:43:37
 *@创建人联系方式：jason4j@qq.com
 *@version
 */
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_RESOURCE")
public class Resource implements Serializable,Comparable<Resource> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 主键
	 */
	@Id
	@GenericGenerator(name="generator",strategy="uuid.hex")
	@GeneratedValue(generator="generator")
	@Column(name="id",nullable=false,unique=true,length=32)
	private String id;
	
	
	/*
	 * 父节点 
	 */
	@Column(name="p_id",nullable=false)
	private String pId;
	
	/*
	 * 资源名字
	 */
	@Column(name="resource_name")
	private String resourceName;
	
	/*
	 * 资源类型，0 菜单 1 操作 2 系统
	 */
	@Column(name="resource_type", nullable = false)
	private Integer resourceType = BigDecimal.ZERO.intValue();
	
	/*
	 * 资源状态 0 可用 1 不可用
	 */
	@Column(name="status", nullable = false)
	private Integer status = BigDecimal.ZERO.intValue();
	
	/*
	 * 是否是基础资源 1是0不是
	 */
	@Column(name="is_sys_resource", nullable = false)
	private boolean sysResource = Boolean.FALSE;
	
	/**
	 * 资源路径
	 */
	@Column(name="url")
	private String url;
	
	/**
	 * 资源排序
	 */
	@Column(name = "sort")
	private Integer sort = BigDecimal.ZERO.intValue();

	/**
	 * 权限字符串 参考shiro的认证方式 例如user:view 可以将权限拓展到按钮操作  
	 */
	@Column(name = "permission",unique=true)
	private String permission;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="query_code",length=27)
	private String queryCode;//查询编码 3位一层 001-999 
	
	@Column(name = "icon",length=20)
	private String resourceIcon;//资源图标

	@Column(name = "type",nullable = false)
	private int type = 0;//0表示平台资源，1表示前台资源
	//@OneToMany(mappedBy="resource",fetch=FetchType.LAZY)
	//@OrderBy(value="sequence")
	//@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	//private List<AuthResRelation> authResRelations;//权限与资源关系表(中间表)
	
	/*@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},mappedBy="resources",targetEntity=Role.class)
	private List<Role> roles;*/
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}*/

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getResourceIcon() {
		return resourceIcon;
	}

	public void setResourceIcon(String resourceIcon) {
		this.resourceIcon = resourceIcon;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if (this.getClass() != obj.getClass()){
			return false;
		}else {
			Resource resource = (Resource) obj;
			return id.equals(resource.id);
		}
	}

	@Override
	public int compareTo(Resource resource) {
		Resource r = resource;
		return sort.compareTo(r.getSort());
	}

	public boolean getSysResource() {
		return sysResource;
	}

	public boolean isSysResource() {
		return sysResource;
	}
	
	public void setSysResource(boolean sysResource) {
		this.sysResource = sysResource;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
