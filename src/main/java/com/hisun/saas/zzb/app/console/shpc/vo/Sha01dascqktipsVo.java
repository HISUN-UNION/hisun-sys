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
    private Sha01dascqkVo sha01dascqkVo;
    private String sha01Id;
    private String tip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sha01dascqkVo getSha01dascqkVo() {
        return sha01dascqkVo;
    }

    public void setSha01dascqk(Sha01dascqkVo sha01dascqkVo) {
        this.sha01dascqkVo = sha01dascqkVo;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getSha01Id() {
        return sha01Id;
    }

    public void setSha01Id(String sha01Id) {
        this.sha01Id = sha01Id;
    }
}
