package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name="APP_SH_PC")
public class Shpc extends TenantEntity implements Serializable{

    public static String SHLX_BWH="1";
    public static String SHLX_CWH="2";

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @Column(name = "PC_MC",length = 255)
    private String pcmc;
    @Column(name = "SHLX",length = 1)
    private String shlx=SHLX_BWH;
    @Column(name = "PC_SJ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pcsj;
    @Column(name = "file_path")
    private String filePath;
    @OneToMany(mappedBy="shpc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01> sha01s;

    @OneToMany(mappedBy="shpc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Shtp> shtps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPcmc() {
        return pcmc;
    }

    public void setPcmc(String pcmc) {
        this.pcmc = pcmc;
    }

    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }

    public Date getPcsj() {
        return pcsj;
    }

    public void setPcsj(Date pcsj) {
        this.pcsj = pcsj;
    }

    public List<Sha01> getSha01s() {
        return sha01s;
    }

    public void setSha01s(List<Sha01> sha01s) {
        this.sha01s = sha01s;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<Shtp> getShtps() {
        return shtps;
    }

    public void setShtps(List<Shtp> shtps) {
        this.shtps = shtps;
    }
}
