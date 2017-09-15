package com.hisun.saas.zzb.app.console.shpc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_SH_A01_SHGX")
public class Sha01shgx {


    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="APP_SH_A01_ID")
    private Sha01 sha01;

    @Column(name = "CW",length = 24)
    private String cw;
    @Column(name = "XM",length = 24)
    private String xm;
    @Column(name = "NL",length = 10)
    private String nl;
    @Column(name = "zzmm",length = 24)
    private String zzmm;
    @Column(name = "GZDWJZW",length = 128)
    private String gzdwjzw;


    @Column(name = "SHGX_PX")
    private int px;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sha01 getSha01() {
        return sha01;
    }

    public void setSha01(Sha01 sha01) {
        this.sha01 = sha01;
    }

    public String getCw() {
        return cw;
    }

    public void setCw(String cw) {
        this.cw = cw;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    public String getGzdwjzw() {
        return gzdwjzw;
    }

    public void setGzdwjzw(String gzdwjzw) {
        this.gzdwjzw = gzdwjzw;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
}
