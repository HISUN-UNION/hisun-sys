package com.hisun.saas.zzb.app.console.aset.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "app_aset_a36")
public class AppAsetA36 extends TenantEntity implements Serializable{


    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    private String id;
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="a01_id")
    private AppAsetA01 appAsetA01;

    @Column(name = "cw",length = 24)
    private String cw;
    @Column(name = "xm",length = 24)
    private String xm;
    @Column(name = "nl",length = 10)
    private String nl;
    @Column(name = "csny",length = 20)
    private String csny;
    @Column(name = "zzmm",length = 24)
    private String zzmm;
    @Column(name = "gzdwjzw",length = 128)
    private String gzdwjzw;
    @Column(name = "shgx_px")
    private int px=0;


    public AppAsetA36(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public AppAsetA01 getAppAsetA01() {
        return appAsetA01;
    }

    public void setAppAsetA01(AppAsetA01 appAsetA01) {
        this.appAsetA01 = appAsetA01;
    }

    public Map<String,String> toSqlFieldMap(){
        Map<String,String> fieldMap = new HashMap<String,String>();
        fieldMap.put("cw",cw);
        fieldMap.put("xm",xm);
        fieldMap.put("nl",nl);
        fieldMap.put("csny",csny);
        fieldMap.put("zzmm",zzmm);
        fieldMap.put("gzdwjzw",gzdwjzw);
        fieldMap.put("shgx_px",String.valueOf(px));
        return fieldMap;
    }

}
