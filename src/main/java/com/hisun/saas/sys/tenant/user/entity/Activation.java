package com.hisun.saas.sys.tenant.user.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 *<p>类名称：Activation</p>
 *<p>类描述: </p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：qinjw
 *@创建时间：2015年3月18日 下午5:01:51
 *@创建人联系方式：qinjw@30wish.net
 *@version
 */

//消除未生成序列ID的警告
@SuppressWarnings("serial")
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_ACTIVATION")

public class Activation implements Serializable {
	
	@Id//主键标识
	@GenericGenerator(name="generator",strategy="uuid.hex")//主键生成器名称及方式
	@GeneratedValue(generator="generator")//主键赋值方式由生成器来进行赋值
	@Column(name="id",nullable=false,unique=true,length=32)//该属性对应数据库列，是否为空，是否唯一等属性
	private String id;//主键

	@Column(name="email",length=256)
	private String email;
	
	@Column(name="status",length=4)
	private String status;//激活状态
	
	@Column(name="invite_user_id",length=64)
	private String inviteUserId;//邀请人的userId
	
	@Column(name="invite_user_name",length=32)
	private String inviteUserName;//邀请人姓名
	
	@Column(name="invite_tenant_id",length=100)
	private String inviteTenantId;//邀请人组织

    @Column(name="role_id", length = 32)
    private String roleId;// 角色
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInviteUserId() {
		return inviteUserId;
	}
	public void setInviteUserId(String inviteUserId) {
		this.inviteUserId = inviteUserId;
	}
	public String getInviteUserName() {
		return inviteUserName;
	}
	public void setInviteUserName(String inviteUserName) {
		this.inviteUserName = inviteUserName;
	}
	public String getInviteTenantId() {
		return inviteTenantId;
	}
	public void setInviteTenantId(String inviteTenantId) {
		this.inviteTenantId = inviteTenantId;
	}

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
