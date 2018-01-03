package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtp;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
    public static String SJLX_GB="1";//干部数据
    public static String SJLX_CL="2";//材料数据
    public static int YSH=1;
    public static int WSH=0;

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
    @Column(name = "sh_zt")//0-未上会，1-已上会
    private int shZt=WSH;
    @Column(name = "SJLX",length = 1)
    private String sjlx=SJLX_GB;
    @Column(name = "PC_PX")//排序
    private int px;

    @Column(name = "mb")
    private String mb;

    @OneToMany(mappedBy="shpc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01> sha01s;

    @OneToMany(mappedBy="shpc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Shtp> shtps;

    @OneToMany(mappedBy="shpc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<ShpcAtts> shpcAttses;


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

    public int getShZt() {
        return shZt;
    }

    public void setShZt(int shZt) {
        this.shZt = shZt;
    }

    public String getSjlx() {
        return sjlx;
    }

    public void setSjlx(String sjlx) {
        this.sjlx = sjlx;
    }

    public List<ShpcAtts> getShpcAttses() {
        return shpcAttses;
    }

    public void setShpcAttses(List<ShpcAtts> shpcAttses) {
        this.shpcAttses = shpcAttses;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

}
