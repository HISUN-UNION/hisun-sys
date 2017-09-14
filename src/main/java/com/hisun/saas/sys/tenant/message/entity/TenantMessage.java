package com.hisun.saas.sys.tenant.message.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>类名称：TenantMessage</p>
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
@Table(name="SYS_TENANT_MESSAGE")
public class TenantMessage extends TenantEntity implements Serializable{

    private static final long serialVersionUID = -5366550138555567752L;
    @Id//主键标识
	@GenericGenerator(name="generator",strategy="uuid.hex")//主键生成器名称及方式
	@GeneratedValue(generator="generator")//主键赋值方式由生成器来进行赋值
	@Column(name="id",nullable=false,unique=true,length=32)//该属性对应数据库列，是否为空，是否唯一等属性
	private String id;//主键
	
	@Column(name="notice_email")
	private boolean noticeEmail = Boolean.FALSE;//是否开启邮件通知
	
	@Column(name="notice_smart")
	private boolean noticeSmart = Boolean.FALSE;//是否开启免打扰模式
	
	@Column(name="notice_sms")
	private boolean noticeSMS = Boolean.FALSE;//是否开启短信通知
	
	@Column(name="notice_express")
	private boolean noticeExpress = Boolean.FALSE;//是否开启每月速递
	
	//@Column(name="notice_daily_mail")
	//private boolean noticeDailyMail = Boolean.FALSE;//是否开启每日邮件提醒
	
	//@Column(name="notice_weekly_mail")
	//private boolean noticeWeeklyMail = Boolean.FALSE;//是否开启每周邮件提醒

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isNoticeEmail() {
		return noticeEmail;
	}

	public void setNoticeEmail(boolean noticeEmail) {
		this.noticeEmail = noticeEmail;
	}

	public boolean isNoticeSmart() {
		return noticeSmart;
	}

	public void setNoticeSmart(boolean noticeSmart) {
		this.noticeSmart = noticeSmart;
	}

	public boolean isNoticeExpress() {
		return noticeExpress;
	}

	public void setNoticeExpress(boolean noticeExpress) {
		this.noticeExpress = noticeExpress;
	}

	public boolean isNoticeSMS() {
		return noticeSMS;
	}

	public void setNoticeSMS(boolean noticeSMS) {
		this.noticeSMS = noticeSMS;
	}

}
