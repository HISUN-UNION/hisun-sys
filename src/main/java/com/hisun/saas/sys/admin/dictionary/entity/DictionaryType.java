package com.hisun.saas.sys.admin.dictionary.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title: DictionaryType.java </p>
 * <p>Package com.hisun.saas.sys.dictionary.entity </p>
 * <p>Description: 字典类型</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 湖南海数互联信息技术有限公司</p>
 * @author Jason
 * @email jason4j@qq.com
 * @date 2015年8月5日 上午9:59:49 
 * @version 
 */
@Entity
@Table(name = "SYS_DICT_TYPE")
public class DictionaryType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false, unique = true,length=32)
	private String id;//逻辑主键
	
	@Column(name="name",length=50)
	private String name;//字典类型名称
	
	@Column(name="remark")
	private String remark;//备注说明
	
	@Column(name="code",nullable = false, unique = true)
	private String code;//字典类型编码
	
	@Column(name = "sort")
	private Integer sort = BigDecimal.ZERO.intValue();//排序
	
	@Column(name="create_time",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();//创建时间
	
	@Column(name="user_id",length=32)
	private String createUser;//创建人

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
