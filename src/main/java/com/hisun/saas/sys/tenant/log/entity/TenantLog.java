package com.hisun.saas.sys.tenant.log.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: TenantLog.java </p>
 * <p>Package com.hisun.cloud.sys.entity </p>
 * <p>Description: 日志记录表</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 湖南海数互联信息技术有限公司</p>
 * @author Jason
 * @email jason4j@qq.com
 * @date 2015年5月20日 下午7:45:28 
 * @version 
 */
@Entity
@Table(name = "SYS_TENANT_LOG")
public class TenantLog extends TenantEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false, unique = true,length=32)
	private String id;//逻辑主键
	
	@Column(name="user_id",length=36)
	private String userId;//操作人
	
	@Column(name="create_time",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();//操作时间
	
	@Column(name="ip")
	private String ip;
	
	@Column(name="type",nullable=false,length=1)
	private short type;//操作类型 1新增 2修改 3删除 4登录 5登出 6异常
	
	@Column(name="content")
	@Type(type="text") 
    @Lob   
    @Basic(fetch = FetchType.LAZY) 
	private String content;//日志内容

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
