package com.hisun.saas.sys.tenant.tenant.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 *<p>类名称：Tenant</p>
 *<p>类描述: </p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：qinjw
 *@创建时间：2015年3月27日 上午9:21:22
 *@创建人联系方式：qinjw@30wish.net
 *@version
 */
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_TENANT")

public class Tenant extends TombstoneEntity implements Serializable {
	
	@Id//主键标识
	@GenericGenerator(name="generator",strategy="uuid.hex")//主键生成器名称及方式
	@GeneratedValue(generator="generator")//主键赋值方式由生成器来进行赋值
	@Column(name="id",nullable=false,unique=true,length=32)//该属性对应数据库列，是否为空，是否唯一等属性
	private String id;//主键
	
	@Column(name="name",length=100)
	private String name;//租户名

	@Column(name="status")
	private int status;//保留以后使用

	@Column(name="token",nullable=false)
	private String token = RandomStringUtils.randomAlphanumeric(32);
	
	public Tenant(){}
	
	public Tenant(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
