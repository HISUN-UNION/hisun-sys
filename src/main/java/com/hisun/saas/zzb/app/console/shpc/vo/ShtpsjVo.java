package com.hisun.saas.zzb.app.console.shpc.vo;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/15.
 */
public class ShtpsjVo{

    private String id;
    private Sha01Vo sha01;
    private ShtpVo shtp;
    private int tp;


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

    public ShtpVo getShtp() {
        return shtp;
    }

    public void setShtp(ShtpVo shtp) {
        this.shtp = shtp;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }
}
