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
@Table(name = "app_zscx_b01")
public class AppZscxB01 extends TenantEntity implements Serializable {
    private String id;
    private String b0101;//名称
    private int px;//排序
    private String comment;//备注

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",nullable = true)
    private AppZscxB01 parentB01;

    @OneToMany(mappedBy = "parentB01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AppZscxB01> childrenB01s;


    @OneToMany(mappedBy = "appZscxB01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AppZscxZs> appZscxZses;


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
    @Column(name = "b0101")
    public String getB0101() {
        return b0101;
    }

    public void setB0101(String b0101) {
        this.b0101 = b0101;
    }

    @Basic
    @Column(name = "px")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    @Basic
    @Column(name = "zs_comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AppZscxB01 getParentB01() {
        return parentB01;
    }

    public void setParentB01(AppZscxB01 parentB01) {
        this.parentB01 = parentB01;
    }

    public List<AppZscxB01> getChildrenB01s() {
        return childrenB01s;
    }

    public void setChildrenB01s(List<AppZscxB01> childrenB01s) {
        this.childrenB01s = childrenB01s;
    }

    public List<AppZscxZs> getAppZscxZses() {
        return appZscxZses;
    }

    public void setAppZscxZses(List<AppZscxZs> appZscxZses) {
        this.appZscxZses = appZscxZses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppZscxB01 that = (AppZscxB01) o;

        if (px != that.px) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (b0101 != null ? !b0101.equals(that.b0101) : that.b0101 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (b0101 != null ? b0101.hashCode() : 0);
        result = 31 * result + px;
        return result;
    }
}
