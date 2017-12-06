package com.hisun.saas.zzb.app.console.gbcx.entity;

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
@Table(name = "app_gbcx_b01")
public class AppGbcxB01 extends TenantEntity implements Serializable {
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    private String id;
    @Column(name = "b0101")
    private String b0101;
    @Column(name = "px")
    private int px;
    @Column(name = "data_type")
    private int dataType;//类型 0--机构 1--分类
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private AppGbcxB01 parentB01;
    @OneToMany(mappedBy = "parentB01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AppGbcxB01> childrenB01s;
    @OneToMany(mappedBy = "appGbcxB01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AppGbcxA01> appGbcxA01s;


   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
   
    public String getB0101() {
        return b0101;
    }

    public void setB0101(String b0101) {
        this.b0101 = b0101;
    }

    

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }



    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }


    public List<AppGbcxA01> getAppGbcxA01s() {
        return appGbcxA01s;
    }

    public void setAppGbcxA01s(List<AppGbcxA01> appGbcxA01s) {
        this.appGbcxA01s = appGbcxA01s;
    }


    public AppGbcxB01 getParentB01() {
        return parentB01;
    }

    public void setParentB01(AppGbcxB01 parentB01) {
        this.parentB01 = parentB01;
    }


    public List<AppGbcxB01> getChildrenB01s() {
        return childrenB01s;
    }

    public void setChildrenB01s(List<AppGbcxB01> childrenB01s) {
        this.childrenB01s = childrenB01s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppGbcxB01 that = (AppGbcxB01) o;

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
