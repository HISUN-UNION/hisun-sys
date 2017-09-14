package com.hisun.saas.sys.admin.communication.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>Title: EmailTemplate.java </p>
 * <p>Package com.hisun.cloud.sys.entity </p>
 * <p>Description: TODO</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 湖南海数互联信息技术有限公司</p>
 * @author Jason
 * @email jason4j@qq.com
 * @date 2015年5月5日 下午3:06:34 
 * @version 
 */
@Entity
@Table(name = "SYS_EMAIL_TEMPLATE")
public class EmailTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false, unique = true,length=32)
	private String id;//逻辑主键

	@Column(name="tpl_content",nullable=false)
	private String tplContent;//模板内容

	@Column(name="subject",nullable=false)
	private String subject;//模板标题

	@Column(name="name",nullable=false)
	private String name;//模板标题

	@Column(name = "permission", nullable = false, length = 50, unique = true)
	private String permission;// 模板唯一标示，给人看

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTplContent() {
		return tplContent;
	}

	public void setTplContent(String tplContent) {
		this.tplContent = tplContent;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
