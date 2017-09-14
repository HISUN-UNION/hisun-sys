package com.hisun.saas.sys.admin.message.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: OnlineMessage.java </p>
 * <p>Package com.hisun.saas.sys.entity </p>
 * <p>Description: 在线消息管理</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 湖南海数互联信息技术有限公司</p>
 * @author Jason
 * @email jason4j@qq.com
 * @date 2015年5月28日 下午5:29:25 
 * @version 
 */
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_ONLINE_MESSAGE")
public class OnlineMessage  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id//主键标识
	@GenericGenerator(name="generator",strategy="uuid.hex")//主键生成器名称及方式
	@GeneratedValue(generator="generator")//主键赋值方式由生成器来进行赋值
	@Column(name="id",nullable=false,unique=true,length=32)//该属性对应数据库列，是否为空，是否唯一等属性
	private String id;//主键
	
	@Column(name="type")
	private short type;//消息类型,0-通知或提示,1-低级别告警消息,2-中级别告警消息,3-高级别告警消息
	
	@Column(name="status")
	private short status = Short.valueOf("0");//消息状态  默认0未读 ,1待处理,2已读
	
	@Column(name="content")
	private String content;//消息内容

	@Column(name="title")
	private String title;//消息标题

	@Column(name="create_user_id")
	protected String createUserId;

	@Column(name="create_user_name",length=32)
	protected String createUserName;
	@Column(name="create_date")

	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
}
