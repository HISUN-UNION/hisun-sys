package com.hisun.saas.sys.tenant.message.entity;

import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>类名称：TenantNotice</p>
 * <p>类描述：消息设置</p>
 * <p>公司：湖南海数互联信息技术有限公司</p>
 *
 * @创建人：lihaiming
 * @创建时间：15/11/20 下午15:40
 * @创建人联系方式：lihm_gz@30wish.net
 */
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_TENANT_NOTICE")
public class TenantNotice implements Serializable{

    private static final long serialVersionUID = -7479432551501269338L;
    @Id
	@GenericGenerator(name="generator",strategy="uuid.hex")
	@GeneratedValue(generator="generator")
	@Column(name="id",nullable=false,unique=true,length=32)
	private String id;
	
	@Column(name="notice_title")
	private String noticeTitle;//通知标题
	
	@Column(name="notice_content")
	@Lob
	@Basic(fetch = FetchType.LAZY)   
	@Type(type="text")  
	private String noticeContent;//通知内容
	
	@Column(name="notice_level")
	private short noticeLevel;//通知等级
	
	@Column(name="push_way")
	private short pushWay;//推送方式  0'短信',1'邮件',2'在线'

	@Column(name="status")
	private boolean status; //公告状态 true显示,false不显示
	
	@Column(name="start_date",nullable=false)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date startDate =  new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tenant_id")
	protected Tenant tenant;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public short getNoticeLevel() {
		return noticeLevel;
	}

	public void setNoticeLevel(short noticeLevel) {
		this.noticeLevel = noticeLevel;
	}

	public short getPushWay() {
		return pushWay;
	}

	public void setPushWay(short pushWay) {
		this.pushWay = pushWay;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
}
