package com.hisun.saas.zzb.app.console.bset.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/12/16.
 */
@Entity
@Table(name = "app_bset_fl")
public class AppBsetFl extends TenantEntity implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    private String id;
    @Column(name = "fl")
    private String fl;
    @Column(name = "px")
    private int px;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private AppBsetFl parentFl;
    @OneToMany(mappedBy = "parentFl", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AppBsetFl> children;
    @OneToMany(mappedBy = "appGbcxFl", fetch = FetchType.LAZY)
    private List<AppBsetFl2B01> appGbcxFl2B01s;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public AppBsetFl getParentFl() {
        return parentFl;
    }

    public void setParentFl(AppBsetFl parentFl) {
        this.parentFl = parentFl;
    }

    public List<AppBsetFl> getChildren() {
        return children;
    }

    public void setChildren(List<AppBsetFl> children) {
        this.children = children;
    }

    public List<AppBsetFl2B01> getAppGbcxFl2B01s() {
        return appGbcxFl2B01s;
    }

    public void setAppGbcxFl2B01s(List<AppBsetFl2B01> appGbcxFl2B01s) {
        this.appGbcxFl2B01s = appGbcxFl2B01s;
    }
}
