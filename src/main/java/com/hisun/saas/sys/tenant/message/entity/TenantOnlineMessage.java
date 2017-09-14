package com.hisun.saas.sys.tenant.message.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>类名称：TenantOnlineMessageService</p>
 * <p>类描述：在线消息管理</p>
 * <p>公司：湖南海数互联信息技术有限公司</p>
 *
 * @创建人：lihaiming
 * @创建时间：15/11/20 下午15:40
 * @创建人联系方式：lihm_gz@30wish.net
 */
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_TENANT_ONLINE_MESSAGE")
public class TenantOnlineMessage extends TenantEntity implements Serializable{

    private static final long serialVersionUID = 2084249843729470802L;
    @Id//主键标识
	@GenericGenerator(name="generator",strategy="uuid.hex")//主键生成器名称及方式
	@GeneratedValue(generator="generator")//主键赋值方式由生成器来进行赋值
	@Column(name="id",nullable=false,unique=true,length=32)//该属性对应数据库列，是否为空，是否唯一等属性
	private String id;//主键
	
	@Column(name="type")
	private short type;//消息类型
	
	@Column(name="status")
	private short status = Short.valueOf("0");//消息状态  默认0未读 ,1待处理,2已读
	
	@Column(name="content")
	private String content;//消息内容

	@Column(name="title")
	private String title;//消息标题
	
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
	
}
