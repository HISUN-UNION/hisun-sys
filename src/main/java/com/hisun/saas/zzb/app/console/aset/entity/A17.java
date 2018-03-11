package com.hisun.saas.zzb.app.console.aset.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2018/3/11.
 */
@Entity
@Table(name = "a17")
public class A17 extends TenantEntity implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    private String id;

    /** 简历 */
    @Column(name = "a17001")
    private String a17001;
    /** 起始日期 */
    @Column(name = "a17005")
    private String a17005;
    /** 截止日期 */
    @Column(name = "a17010")
    private String a17010;
    /** 曾在单位 */
    @Column(name = "a17015")
    private String a17015;
    /** 曾在单位代码 */
    @Column(name = "a17016")
    private String a17016;
    /** 曾经从事工作或担任职务 */
    @Column(name = "a17020")
    private String a17020;
    /** 曾在单位职务类别 */
    @Column(name = "a17022")
    private String a17022;
    /** 简历证明人 */
    @Column(name = "a17025")
    private String a17025;


    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="app_aset_a01_id")
    private AppAsetA01 appAsetA01;


    public String getA17001() {
        return a17001;
    }

    public void setA17001(String a17001) {
        this.a17001 = a17001;
    }

    public String getA17005() {
        return a17005;
    }

    public void setA17005(String a17005) {
        this.a17005 = a17005;
    }

    public String getA17010() {
        return a17010;
    }

    public void setA17010(String a17010) {
        this.a17010 = a17010;
    }

    public String getA17015() {
        return a17015;
    }

    public void setA17015(String a17015) {
        this.a17015 = a17015;
    }

    public String getA17016() {
        return a17016;
    }

    public void setA17016(String a17016) {
        this.a17016 = a17016;
    }

    public String getA17020() {
        return a17020;
    }

    public void setA17020(String a17020) {
        this.a17020 = a17020;
    }

    public String getA17022() {
        return a17022;
    }

    public void setA17022(String a17022) {
        this.a17022 = a17022;
    }

    public String getA17025() {
        return a17025;
    }

    public void setA17025(String a17025) {
        this.a17025 = a17025;
    }
}
