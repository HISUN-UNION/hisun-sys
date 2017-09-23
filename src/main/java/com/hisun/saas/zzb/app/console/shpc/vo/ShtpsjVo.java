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
    private Sha01Vo sha01Vo;
    private ShtpVo shtpVo;
    private int tp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sha01Vo getSha01Vo() {
        return sha01Vo;
    }

    public void setSha01Vo(Sha01Vo sha01Vo) {
        this.sha01Vo = sha01Vo;
    }

    public ShtpVo getShtpVo() {
        return shtpVo;
    }

    public void setShtpVo(ShtpVo shtpVo) {
        this.shtpVo = shtpVo;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }
}
