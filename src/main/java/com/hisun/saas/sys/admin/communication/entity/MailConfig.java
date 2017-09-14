package com.hisun.saas.sys.admin.communication.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SYS_MAIL_CONFIG")
public class MailConfig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false, unique = true,length=32)
	private String id;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "account", nullable = false, length = 50)
	private String account;// 发件人账户
	
	@Column(name = "password", nullable = false, length = 50)
	private String password;// 密码
	
	@Column(name = "email", nullable = false, length = 50)
	private String email;// 发件人邮箱
	
	@Column(name = "email_server", nullable = false, length = 50)
	private String emailServer;// SMTP

	@Column(name="status",nullable=false)
	private Boolean status;//是否启用

	@Column(name = "mail_server")
	private String mailServer;//邮件服务器api URL

	@Column(name = "version")
	private String version;// version

	@Column(name = "api_user")
	private String apiUser;

	@Column(name = "api_key")
	private String apiKey;

	@Column(name = "send_uri")
	private String send;

	@Column(name = "send_template_uri")
	private String sendTemplate;

	@Column(name = "add_template_uri")
	private String addTemplate;

	@Column(name = "delete_template_uri")
	private String deleteTemplate;

	@Column(name = "update_template_uri")
	private String updateTemplate;

	@Column(name = "get_template_uri")
	private String getTemplate;

	@Column(name = "type", nullable = false)
	private boolean type=Boolean.FALSE;//默认触发类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailServer() {
		return emailServer;
	}

	public void setEmailServer(String emailServer) {
		this.emailServer = emailServer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMailServer() {
		return mailServer;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public String getApiUser() {
		return apiUser;
	}

	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getSendTemplate() {
		return sendTemplate;
	}

	public void setSendTemplate(String sendTemplate) {
		this.sendTemplate = sendTemplate;
	}

	public String getAddTemplate() {
		return addTemplate;
	}

	public void setAddTemplate(String addTemplate) {
		this.addTemplate = addTemplate;
	}

	public String getDeleteTemplate() {
		return deleteTemplate;
	}

	public void setDeleteTemplate(String deleteTemplate) {
		this.deleteTemplate = deleteTemplate;
	}

	public String getUpdateTemplate() {
		return updateTemplate;
	}

	public void setUpdateTemplate(String updateTemplate) {
		this.updateTemplate = updateTemplate;
	}

	public String getGetTemplate() {
		return getTemplate;
	}

	public void setGetTemplate(String getTemplate) {
		this.getTemplate = getTemplate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isType() {
		return type;
	}

	public boolean getType() {
		return type;
	}


	public void setType(boolean type) {
		this.type = type;
	}
}
