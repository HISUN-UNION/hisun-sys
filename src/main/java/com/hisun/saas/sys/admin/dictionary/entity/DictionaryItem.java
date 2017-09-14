package com.hisun.saas.sys.admin.dictionary.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title: DictionaryItem.java </p>
 * <p>Package com.hisun.saas.sys.dictionary.entity </p>
 * <p>Description: 字典项</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 湖南海数互联信息技术有限公司</p>
 * @author Jason
 * @email jason4j@qq.com
 * @date 2015年8月5日 上午10:26:20 
 * @version 
 */
@Entity
@Table(name = "SYS_DICT_ITEM")
public class DictionaryItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", nullable = false, unique = true,length=32)
	private String id;//逻辑主键
	
	@Column(name = "p_id", length=32)
	private String pId;//父id
	
	@ManyToOne
	@JoinColumn(name="dict_type_id")
	private DictionaryType dictionaryType;
	
	@Column(name="item",length=50)
	private String item;//字典项
	
	@Column(name="value",length=50)
	private String value;//字典项值
	
	@Column(name="remark")
	private String remark;//备注说明
	
	@Column(name = "sort")
	private Integer sort = BigDecimal.ZERO.intValue();//排序
	
	@Column(name="query_code",unique=true,length=27, nullable = false)
	private String queryCode;
	
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

	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}
}
