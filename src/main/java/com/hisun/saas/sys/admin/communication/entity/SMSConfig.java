package com.hisun.saas.sys.admin.communication.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_SMS_CONFIG")
public class SMSConfig implements Serializable {

	private static final long serialVersionUID = 686169949853994911L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false, unique = true,length=32)
	private String id;

	@Column(name="sms_name",nullable=false)
	private String smsName;//名字
	
	@Column(name = "sms_server", nullable = false, length = 50)
	private String smsServer;// 服务器地址

	@Column(name = "apikey", nullable = false, length = 50)
	private String apikey;

	@Column(name = "version", nullable = false, length = 50)
	private String version;// version
	
	@Column(name="status",nullable=false)
	private Boolean status = Boolean.FALSE;//是否启用

	@Column(name = "send_uri", length = 50)
	private String send;

	@Column(name = "tpl_send_uri", length = 50)
	private String tplSend;

	@Column(name = "get_tpl_uri", length = 50)
	private String getTpl;

	@Column(name = "add_tpl_uri", length = 50)
	private String addTpl;

	@Column(name = "update_tpl_uri", length = 50)
	private String updateTpl;

	@Column(name = "del_tpl_uri", length = 50)
	private String delTpl;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSmsServer() {
		return smsServer;
	}

	public void setSmsServer(String smsServer) {
		this.smsServer = smsServer;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSmsName() {
		return smsName;
	}

	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getTplSend() {
		return tplSend;
	}

	public void setTplSend(String tplSend) {
		this.tplSend = tplSend;
	}

	public String getGetTpl() {
		return getTpl;
	}

	public void setGetTpl(String getTpl) {
		this.getTpl = getTpl;
	}

	public String getAddTpl() {
		return addTpl;
	}

	public void setAddTpl(String addTpl) {
		this.addTpl = addTpl;
	}

	public String getUpdateTpl() {
		return updateTpl;
	}

	public void setUpdateTpl(String updateTpl) {
		this.updateTpl = updateTpl;
	}

	public String getDelTpl() {
		return delTpl;
	}

	public void setDelTpl(String delTpl) {
		this.delTpl = delTpl;
	}
}