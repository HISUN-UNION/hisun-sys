package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "app_mc_a01_shgx")
public class GbMcA01shgx extends TenantEntity implements Serializable{


    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    private String id;
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="app_mc_a01_id")
    private GbMcA01 gbMcA01;

    @Column(name = "cw",length = 24)
    private String cw;
    @Column(name = "xm",length = 24)
    private String xm;
    @Column(name = "nl",length = 10)
    private String nl;
    @Column(name = "zzmm",length = 24)
    private String zzmm;
    @Column(name = "gzdwjzw",length = 128)
    private String gzdwjzw;

    @Column(name = "shgx_px")
    private int px;


    public GbMcA01shgx(){}

    public GbMcA01shgx(Map<String,String> ywJsonMap, int px){
        this.cw = ywJsonMap.get("call_"+px);
        this.xm =  ywJsonMap.get("name_"+px);
        this.nl = ywJsonMap.get("age_"+px);
        this.zzmm = ywJsonMap.get("polity_"+px);
        this.gzdwjzw = ywJsonMap.get("job_"+px);
        this.px = px;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GbMcA01 getGbMcA01() {
        return gbMcA01;
    }

    public void setGbMcA01(GbMcA01 gbMcA01) {
        this.gbMcA01 = gbMcA01;
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
