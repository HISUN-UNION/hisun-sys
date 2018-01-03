package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_SH_A01_DASCQK_TIPS")
public class Sha01dascqktips extends TenantEntity implements Serializable {

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @ManyToOne(optional=true,fetch=FetchType.LAZY)
    @JoinColumn(name="APP_SH_A01_DASCQK_ID")
    private Sha01dascqk sha01dascqk;
    @Column(name = "TIP",length = 255)
    private String tip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sha01dascqk getSha01dascqk() {
        return sha01dascqk;
    }

    public void setSha01dascqk(Sha01dascqk sha01dascqk) {
        this.sha01dascqk = sha01dascqk;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


}
