package com.hisun.saas.zzb.app.console.bset.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/12/16.
 */
@Entity
@Table(name = "app_bset_fl_2_b01")
public class AppBsetFl2B01 extends TenantEntity implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    private String id;
    @ManyToOne
    private AppBsetFl appGbcxFl;
    @ManyToOne
    private AppBsetB01 appGbcxB01;
    @Column(name = "px")
    private int px;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppBsetFl getAppGbcxFl() {
        return appGbcxFl;
    }

    public void setAppGbcxFl(AppBsetFl appGbcxFl) {
        this.appGbcxFl = appGbcxFl;
    }

    public AppBsetB01 getAppGbcxB01() {
        return appGbcxB01;
    }

    public void setAppGbcxB01(AppBsetB01 appGbcxB01) {
        this.appGbcxB01 = appGbcxB01;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
}
