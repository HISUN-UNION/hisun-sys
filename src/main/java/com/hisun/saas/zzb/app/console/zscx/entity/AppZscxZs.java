package com.hisun.saas.zzb.app.console.zscx.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/11/28.
 */
@Entity
@Table(name = "app_zscx_zs")
public class AppZscxZs extends TenantEntity implements Serializable {

    private String id;
    private String zwmc;//职务名称
    private int xp;//现配
    private int sp;//实配
    private int cqb;//超缺编

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "b01_id")
    private AppZscxB01 appZscxB01;

    @OneToMany(mappedBy = "appZscxZs",fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AppZscxZsA01> appZscxZsA01s;

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "zwmc")
    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }

    @Basic
    @Column(name = "xp")
    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    @Basic
    @Column(name = "sp")
    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    @Basic
    @Column(name = "cqb")
    public int getCqb() {
        return cqb;
    }

    public void setCqb(int cqb) {
        this.cqb = cqb;
    }

    public AppZscxB01 getAppZscxB01() {
        return appZscxB01;
    }

    public void setAppZscxB01(AppZscxB01 appZscxB01) {
        this.appZscxB01 = appZscxB01;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppZscxZs appZscxZs = (AppZscxZs) o;

        if (xp != appZscxZs.xp) return false;
        if (sp != appZscxZs.sp) return false;
        if (cqb != appZscxZs.cqb) return false;
        if (id != null ? !id.equals(appZscxZs.id) : appZscxZs.id != null) return false;
        if (zwmc != null ? !zwmc.equals(appZscxZs.zwmc) : appZscxZs.zwmc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (zwmc != null ? zwmc.hashCode() : 0);
        result = 31 * result + xp;
        result = 31 * result + sp;
        result = 31 * result + cqb;
        return result;
    }
}
