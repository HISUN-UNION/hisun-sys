package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_SH_A01_GBRMSPB")
public class Sha01gbrmspb extends TenantEntity implements Serializable{

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="APP_SH_A01_ID")
    private Sha01 sha01;
    @Column(name = "XM",length = 20)
    private String xm;
    @Column(name = "XB",length = 10)
    private String xb;
    @Column(name = "CSNY",length = 24)
    private String csny;
    @Column(name = "NL",length = 10)
    private String nl;
    @Column(name = "XB",length = 128)
    private String zppath;
    @Column(name = "MZ",length = 24)
    private String mz;
    @Column(name = "JG",length = 24)
    private String jg;
    @Column(name = "CSD",length = 24)
    private String csd;


    @Column(name = "RDSJ",length = 10)
    private String rdsj;
    @Column(name = "CJGZSJ",length = 10)
    private String cjgzsj;
    @Column(name = "JKZK",length = 24)
    private String jkzk;
    @Column(name = "ZYJSZW",length = 60)
    private String zyjszw;
    @Column(name = "ZYTC",length = 60)
    private String zytc;
    @Column(name = "XL_QRZ",length = 24)
    private String xlqrz;
    @Column(name = "XW_QRZ",length = 24)
    private String xwqrz;
    @Column(name = "XLZZ",length = 24)
    private String xlzz;


    @Column(name = "XW_ZZ",length = 24)
    private String xwzz;
    @Column(name = "QRZ_BYYX",length = 128)
    private String  qrz_byyx;
    @Column(name = "ZZ_BYYX",length = 128)
    private String zz_byyx;
    @Column(name = "XRZW",length = 128)
    private String xrzw;
    @Column(name = "NRZW",length = 128)
    private String nrzw;
    @Column(name = "RMLY",length = 255)
    private String rmly;
    @Column(name = "CBDWYJ",length = 255)
    private String cbdwyj;
    @Column(name = "SPJGYJ",length = 24)
    private String spjgyj;
    @Column(name = "XZJGRMYJ",length = 24)
    private String xzjgrmyj;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sha01 getSha01() {
        return sha01;
    }

    public void setSha01(Sha01 sha01) {
        this.sha01 = sha01;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getZppath() {
        return zppath;
    }

    public void setZppath(String zppath) {
        this.zppath = zppath;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }

    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }

    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }

    public String getJkzk() {
        return jkzk;
    }

    public void setJkzk(String jkzk) {
        this.jkzk = jkzk;
    }

    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }

    public String getZytc() {
        return zytc;
    }

    public void setZytc(String zytc) {
        this.zytc = zytc;
    }

    public String getXlqrz() {
        return xlqrz;
    }

    public void setXlqrz(String xlqrz) {
        this.xlqrz = xlqrz;
    }

    public String getXwqrz() {
        return xwqrz;
    }

    public void setXwqrz(String xwqrz) {
        this.xwqrz = xwqrz;
    }

    public String getXlzz() {
        return xlzz;
    }

    public void setXlzz(String xlzz) {
        this.xlzz = xlzz;
    }

    public String getXwzz() {
        return xwzz;
    }

    public void setXwzz(String xwzz) {
        this.xwzz = xwzz;
    }

    public String getQrz_byyx() {
        return qrz_byyx;
    }

    public void setQrz_byyx(String qrz_byyx) {
        this.qrz_byyx = qrz_byyx;
    }

    public String getZz_byyx() {
        return zz_byyx;
    }

    public void setZz_byyx(String zz_byyx) {
        this.zz_byyx = zz_byyx;
    }

    public String getXrzw() {
        return xrzw;
    }

    public void setXrzw(String xrzw) {
        this.xrzw = xrzw;
    }

    public String getNrzw() {
        return nrzw;
    }

    public void setNrzw(String nrzw) {
        this.nrzw = nrzw;
    }

    public String getRmly() {
        return rmly;
    }

    public void setRmly(String rmly) {
        this.rmly = rmly;
    }

    public String getCbdwyj() {
        return cbdwyj;
    }

    public void setCbdwyj(String cbdwyj) {
        this.cbdwyj = cbdwyj;
    }

    public String getSpjgyj() {
        return spjgyj;
    }

    public void setSpjgyj(String spjgyj) {
        this.spjgyj = spjgyj;
    }

    public String getXzjgrmyj() {
        return xzjgrmyj;
    }

    public void setXzjgrmyj(String xzjgrmyj) {
        this.xzjgrmyj = xzjgrmyj;
    }
}
