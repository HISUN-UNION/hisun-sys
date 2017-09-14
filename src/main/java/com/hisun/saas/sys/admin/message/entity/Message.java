package com.hisun.saas.sys.admin.message.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>Title: Message.java </p>
 * <p>Package com.hisun.saas.sys.entity </p>
 * <p>Description: 消息设置</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 湖南海数互联信息技术有限公司</p>
 * @author Jason
 * @email jason4j@qq.com
 * @date 2015年5月29日 上午11:00:59 
 * @version 
 */
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_MESSAGE")
public class Message extends TenantEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
