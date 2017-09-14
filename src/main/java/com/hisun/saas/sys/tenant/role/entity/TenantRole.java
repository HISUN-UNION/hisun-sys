package com.hisun.saas.sys.tenant.role.entity;

import com.hisun.base.entity.AbstractRole;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>类名称:TenantRole</p>
 * <p>类描述:</p>
 * <p>公司:湖南海数互联信息技术有限公司</p>
 *
 * @创建者:init
 * @创建人:15/11/18上午11:32
 * @创建人联系方式:init@hn-hisun.com
 */
@Entity
@Table(name="SYS_TENANT_ROLE")
public class TenantRole extends AbstractRole implements Serializable{

}
