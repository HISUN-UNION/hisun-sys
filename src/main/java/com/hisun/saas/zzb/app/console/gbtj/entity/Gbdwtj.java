package com.hisun.saas.zzb.app.console.gbtj.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/15.
 */
@Entity
@Table(name = "APP_DWJG_TJ")
public class Gbdwtj extends TenantEntity implements Serializable{

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, unique = true, length = 32)
    private String id;
    @Column(name = "TJ_MC",length = 128)
    private String tjmc;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    @Column(name = "TJ_JSON_DATA",columnDefinition = "CLOB",nullable = true)
    private String tjjsondata;

    @Column(name = "TJ_PX")
    private int px;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTjmc() {
        return tjmc;
    }

    public void setTjmc(String tjmc) {
        this.tjmc = tjmc;
    }

    public String getTjjsondata() {
        return tjjsondata;
    }

    public void setTjjsondata(String tjjsondata) {
        this.tjjsondata = tjjsondata;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
}
