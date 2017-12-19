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
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fl_id")
    private AppBsetFl appBsetFl;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "b01_id")
    private AppBsetB01 appBsetB01;
    @Column(name = "px")
    private int px;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public AppBsetFl getAppBsetFl() {
        return appBsetFl;
    }

    public void setAppBsetFl(AppBsetFl appBsetFl) {
        this.appBsetFl = appBsetFl;
    }

    public AppBsetB01 getAppBsetB01() {
        return appBsetB01;
    }

    public void setAppBsetB01(AppBsetB01 appBsetB01) {
        this.appBsetB01 = appBsetB01;
    }
}
