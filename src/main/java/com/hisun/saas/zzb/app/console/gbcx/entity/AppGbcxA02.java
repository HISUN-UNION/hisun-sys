package com.hisun.saas.zzb.app.console.gbcx.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/12/12.
 */
@Entity
@Table(name = "app_gbcx_a02")
public class AppGbcxA02  extends TenantEntity implements Serializable {
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    private String id;
    @Column(name = "zwmc")
    private String zwmc;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "a01_id")
    private AppGbcxA01 appGbcxA01;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "b01_id")
    private AppGbcxB01 appGbcxB01;


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

    public AppGbcxA01 getAppGbcxA01() {
        return appGbcxA01;
    }

    public void setAppGbcxA01(AppGbcxA01 appGbcxA01) {
        this.appGbcxA01 = appGbcxA01;
    }

    public AppGbcxB01 getAppGbcxB01() {
        return appGbcxB01;
    }

    public void setAppGbcxB01(AppGbcxB01 appGbcxB01) {
        this.appGbcxB01 = appGbcxB01;
    }
}
