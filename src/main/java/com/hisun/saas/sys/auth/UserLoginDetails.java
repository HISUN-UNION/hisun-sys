package com.hisun.saas.sys.auth;

import com.hisun.saas.sys.admin.resource.entity.Resource;
import com.hisun.base.auth.AbstractUserLoginDetails;
import com.hisun.base.entity.AbstractRole;
import com.hisun.base.entity.AbstractUser;
import com.hisun.saas.sys.admin.Constants;
import com.hisun.saas.sys.admin.resource.vo.ResourceMenuItem;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.util.BeanMapper;

import java.util.ArrayList;
import java.util.List;

public class UserLoginDetails extends AbstractUserLoginDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private AbstractUser user;
    private Tenant tenant;
    private List<Resource> resources;
    private List<AbstractRole> roles;

    private int type;//类型

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<AbstractRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AbstractRole> roles) {
        this.roles = roles;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public AbstractUser getUser() {
        return user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ResourceMenuItem> getResourceMenuItems() {
        List<ResourceMenuItem> items = new ArrayList<ResourceMenuItem>();
        for (Resource resource : resources) {
            if (resource.getResourceType() == Constants.RESOURCE_APP) {//系统 顶层菜单
                ResourceMenuItem item = new ResourceMenuItem();
                BeanMapper.copy(resource, item);
                item.setResId(resource.getId());
                item.setTop(true);
                item.setPermission(resource.getPermission());
                setAllChildren(item);
                items.add(item);
            }
        }
        return items;
    }

    private void setAllChildren(ResourceMenuItem node) {
        List<ResourceMenuItem> children = new ArrayList<ResourceMenuItem>();
        for (Resource resource : resources) {
            if (resource.getpId().equals(node.getResId()) && resource.getResourceType() != 1) {
                ResourceMenuItem child = new ResourceMenuItem();
                BeanMapper.copy(resource, child);
                child.setResId(resource.getId());
                setAllChildren(child);
                children.add(child);
            }
        }
        node.setChildren(children);
    }

}