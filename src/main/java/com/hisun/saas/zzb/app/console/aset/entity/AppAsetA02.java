package com.hisun.saas.zzb.app.console.aset.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/12/12.
 */
@Entity
@Table(name = "app_aset_a02")
public class AppAsetA02 extends TenantEntity implements Serializable {
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    private String id;
    @Column(name = "zwmc")
    private String zwmc;
    @Column(name = "rzsj")
    private String rzsj;//任职时间
    @Column(name = "jtl_px")
    private int jtlPx;//集体类排列顺序
    @Column(name = "px")
    private int px;//个人职务排序


    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "a01_id")
    private AppAsetA01 appAsetA01;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "b01_id")
    private AppBsetB01 appBsetB01;


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

    public AppAsetA01 getAppAsetA01() {
        return appAsetA01;
    }

    public void setAppAsetA01(AppAsetA01 appAsetA01) {
        this.appAsetA01 = appAsetA01;
    }

    public AppBsetB01 getAppBsetB01() {
        return appBsetB01;
    }

    public void setAppBsetB01(AppBsetB01 appBsetB01) {
        this.appBsetB01 = appBsetB01;
    }

    public int getJtlPx() {
        return jtlPx;
    }

    public void setJtlPx(int jtlPx) {
        this.jtlPx = jtlPx;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public String getRzsj() {
        return rzsj;
    }

    public void setRzsj(String rzsj) {
        this.rzsj = rzsj;
    }

    public String toSqliteInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_aset_a02 ");
        sb.append("(");
        sb.append("id");
        sb.append(",a01_id");
        sb.append(",b01_id");
        sb.append(",zwmc");
        sb.append(",rzsj");
        sb.append(",px");
        sb.append(",jtl_px");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(id)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(appAsetA01.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(appBsetB01.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zwmc)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(rzsj)+"'");
        sb.append(","+px);
        sb.append(","+jtlPx);
        sb.append(")");
        return sb.toString();
    }
}
