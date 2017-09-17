package com.hisun.saas.zzb.app.console.shpc.vo;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
public class ShpcVo extends TenantEntity implements Serializable{

    public static String SHLX_BWH="1";
    public static String SHLX_CWH="2";
    private String id;
    private String pcmc;
    private String shlx;
    private String pcsjValue;
    private String filePath;
    private int a01Count;
    private List<Sha01Vo> sha01s;

    @OneToMany(mappedBy="shpc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<ShtpVo> shtps;

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

    public String getPcsjValue() {
        return pcsjValue;
    }

    public void setPcsjValue(String pcsjValue) {
        this.pcsjValue = pcsjValue;
    }

    public List<Sha01Vo> getSha01s() {
        return sha01s;
    }

    public void setSha01s(List<Sha01Vo> sha01s) {
        this.sha01s = sha01s;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<ShtpVo> getShtps() {
        return shtps;
    }

    public void setShtps(List<ShtpVo> shtps) {
        this.shtps = shtps;
    }

    public int getA01Count() {
        return a01Count;
    }

    public void setA01Count(int a01Count) {
        this.a01Count = a01Count;
    }
}