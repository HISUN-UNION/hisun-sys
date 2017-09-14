package com.hisun.saas.sys.admin.user.entity;

import com.hisun.saas.sys.admin.role.entity.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 *<p>类名称：UserRoleRelation</p>
 *<p>类描述: </p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：qinjw
 *@创建时间：2015-3-18 上午10:33:57
 *@创建人联系方式：qinjw@30wish.net
 *@version
 */

//消除未生成序列ID的警告
@SuppressWarnings("serial")
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_USER_ROLE")
public class UserRole implements Serializable {
	
	@Id//主键标识
	@GenericGenerator(name="generator",strategy="uuid.hex")//主键生成器名称及方式
	@GeneratedValue(generator="generator")//主键赋值方式由生成器来进行赋值
	@Column(name="id",nullable=false,unique=true,length=32)//该属性对应数据库列，是否为空，是否唯一等属性
	private String id;//主键
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;//

	@ManyToOne(optional=true,fetch=FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;//

	//@Column(name="sequence")
	//private int sequence;//顺序

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/*public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}*/
	
}
