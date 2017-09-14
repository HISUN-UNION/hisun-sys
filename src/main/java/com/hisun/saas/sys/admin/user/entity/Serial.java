package com.hisun.saas.sys.admin.user.entity;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: Serial.java </p>
 * <p>Package com.hisun.saas.sys.entity </p>
 * <p>Description: 找回密码的序列号生成器,后期可用缓存替代</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 湖南海数互联信息技术有限公司</p>
 * @author Jason
 * @email jason4j@qq.com
 * @date 2015年4月28日 下午3:12:54 
 * @version 
 */

//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_SERIAL")
public class Serial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id//主键标识
	@GenericGenerator(name="generator",strategy="uuid.hex")//主键生成器名称及方式
	@GeneratedValue(generator="generator")//主键赋值方式由生成器来进行赋值
	@Column(name="id",nullable=false,unique=true,length=32)//该属性对应数据库列，是否为空，是否唯一等属性
	private String id;//主键

	@Column(name="email",length=256,nullable=false)
	private String email;
	
	@Column(name="status")
	private boolean status = Boolean.FALSE;//是否过期
	
	@Column(name="satrt_date",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date satrtDate = new Date();//记录发送邮件的日期
	
	@Column(name="end_date",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate = new DateTime().plusDays(1).toDate();//有效期为一天
	
	@Column(name="serial_key",length=160,nullable=false)
	private String key;//序列号

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getSatrtDate() {
		return satrtDate;
	}

	public void setSatrtDate(Date satrtDate) {
		this.satrtDate = satrtDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
