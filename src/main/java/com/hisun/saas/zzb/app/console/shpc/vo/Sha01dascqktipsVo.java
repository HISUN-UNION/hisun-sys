package com.hisun.saas.zzb.app.console.shpc.vo;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
public class Sha01dascqktipsVo{

    private String id;
    private Sha01dascqkVo sha01dascqk;
    private String tip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sha01dascqkVo getSha01dascqk() {
        return sha01dascqk;
    }

    public void setSha01dascqk(Sha01dascqkVo sha01dascqk) {
        this.sha01dascqk = sha01dascqk;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
