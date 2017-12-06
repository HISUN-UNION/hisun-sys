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
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    private String id;
    @Column(name = "zwmc")
    private String zwmc;//职务名称
    @Column(name = "xp")
    private int xp;//现配
    @Column(name = "sp")
    private int sp;//实配
    @Column(name = "cqb")
    private int cqb;//超缺编

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "b01_id")
    private AppZscxB01 appZscxB01;
    @OneToMany(mappedBy = "appZscxZs",fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AppZscxZsA01> appZscxZsA01s;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }



    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }



    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }



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


    public List<AppZscxZsA01> getAppZscxZsA01s() {
        return appZscxZsA01s;
    }

    public void setAppZscxZsA01s(List<AppZscxZsA01> appZscxZsA01s) {
        this.appZscxZsA01s = appZscxZsA01s;
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
