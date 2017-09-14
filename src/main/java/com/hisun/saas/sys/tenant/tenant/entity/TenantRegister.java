package com.hisun.saas.sys.tenant.tenant.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>类名称:TenantRegister</p>
 * <p>类描述:</p>
 * <p>公司:湖南海数互联信息技术有限公司</p>
 *
 * @创建者:init
 * @创建人:15/11/21上午9:36
 * @创建人联系方式:init@hn-hisun.com
 */
@Entity
@Table(name="SYS_TENANT_REGISTER")
public class TenantRegister implements Serializable{

    public static int STATUS_NO = 0;//未激活

    public static int STATUS_YES = 1;//已激活

    @Id//主键标识
    @GenericGenerator(name="generator",strategy="uuid.hex")//主键生成器名称及方式
    @GeneratedValue(generator="generator")//主键赋值方式由生成器来进行赋值
    @Column(name="id",nullable=false,unique=true,length=32)//该属性对应数据库列，是否为空，是否唯一等属性
    private String id;//主键

    @Column(name="tenant_name")
    private String tenantName;

    @Column(name="email")
    private String email;

    @Column(name="username")
    private String username;

    @Column(name="realname")
    private String realname;

    @Column(name="password")
    private String password;

    @Column(name="salt")
    private String salt;

    @Column(name="tel")
    private String tel;

    @Column(name="status")
    private int status;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="activateDate")
    private Date activateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
