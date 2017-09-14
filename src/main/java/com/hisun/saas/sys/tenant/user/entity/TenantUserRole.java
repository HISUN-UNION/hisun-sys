package com.hisun.saas.sys.tenant.user.entity;

import com.hisun.saas.sys.tenant.role.entity.TenantRole;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>类名称:TenantUserRole</p>
 * <p>类描述:</p>
 * <p>公司:湖南海数互联信息技术有限公司</p>
 *
 * @创建者:init
 * @创建人:15/11/18上午11:32
 * @创建人联系方式:init@hn-hisun.com
 */
@Entity
@Table(name = "SYS_TENANT_USER_ROLE")
public class TenantUserRole implements Serializable{

    @Id//主键标识
    @GenericGenerator(name="generator",strategy="uuid.hex")//主键生成器名称及方式
    @GeneratedValue(generator="generator")//主键赋值方式由生成器来进行赋值
    @Column(name="id",nullable=false,unique=true,length=32)//该属性对应数据库列，是否为空，是否唯一等属性
    private String id;//主键

    @ManyToOne(targetEntity = TenantUser.class)
    @JoinColumn(name="tenant_user_id")
    private TenantUser user;

    @ManyToOne(targetEntity =  TenantRole.class)
    @JoinColumn(name="tenant_role_id")
    private TenantRole role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TenantUser getUser() {
        return user;
    }

    public void setUser(TenantUser user) {
        this.user = user;
    }

    public TenantRole getRole() {
        return role;
    }

    public void setRole(TenantRole role) {
        this.role = role;
    }
}
