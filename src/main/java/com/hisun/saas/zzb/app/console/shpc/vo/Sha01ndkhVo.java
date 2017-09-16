package com.hisun.saas.zzb.app.console.shpc.vo;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
public class Sha01ndkhVo{
    private String id;
    private Sha01Vo sha01;

    private String nd;
    private String khjg;
    private int px;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sha01Vo getSha01() {
        return sha01;
    }

    public void setSha01(Sha01Vo sha01) {
        this.sha01 = sha01;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getKhjg() {
        return khjg;
    }

    public void setKhjg(String khjg) {
        this.khjg = khjg;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
}
