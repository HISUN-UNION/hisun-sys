package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhouying on 2017/9/15.
 */
@Entity
@Table(name = "APP_SH_TP")
public class Shtp extends TenantEntity implements Serializable{
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @ManyToOne(optional=true,fetch=FetchType.LAZY)
    @JoinColumn(name="APP_SH_PC_ID")
    private Shpc shpc;
    @Column(name = "TPQ_BH",length = 128)
    private String tpq_bh;
    @Column(name = "TPR_ID",length = 32)
    private String tpr_id;
    @Column(name = "TPR_XM",length = 64)
    private String tpr_xm;
    @Column(name = "TP_SJ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tp_sj;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTpq_bh() {
        return tpq_bh;
    }

    public void setTpq_bh(String tpq_bh) {
        this.tpq_bh = tpq_bh;
    }

    public String getTpr_id() {
        return tpr_id;
    }

    public void setTpr_id(String tpr_id) {
        this.tpr_id = tpr_id;
    }

    public String getTpr_xm() {
        return tpr_xm;
    }

    public void setTpr_xm(String tpr_xm) {
        this.tpr_xm = tpr_xm;
    }

    public Date getTp_sj() {
        return tp_sj;
    }

    public void setTp_sj(Date tp_sj) {
        this.tp_sj = tp_sj;
    }

    public Shpc getShpc() {
        return shpc;
    }

    public void setShpc(Shpc shpc) {
        this.shpc = shpc;
    }
}
