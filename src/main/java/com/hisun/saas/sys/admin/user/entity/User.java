package com.hisun.saas.sys.admin.user.entity;

import com.hisun.base.entity.AbstractUser;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 
 *<p>类名称：User</p>
 *<p>类描述: 平台用户表</p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：Jason
 *@创建时间：2015年11月18日 下午11:40:56
 *@创建人联系方式：jason4j@qq.com
 *@version v0.1
 */

//消除未生成序列ID的警告
@SuppressWarnings("serial")
//标识该对象为实体对象，以便被HIBERNATE扫描器扫描到并解析加载
@Entity
//实体对应的数据库表
@Table(name="SYS_USER")
//@DynamicUpdate
public class User extends AbstractUser implements Serializable {

    @Column(name="is_admin")
    private boolean admin = false;

    @OneToMany(mappedBy="user",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    private List<UserRole> userRoles;//用户拥有的角色(中间表)

    public User(){}

    public User(String id){
         setId(id);
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getExistName(){
        return StringUtils.isNotBlank(getUsername())?getUsername():getRealname();
    }
}