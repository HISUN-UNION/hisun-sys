package com.hisun.saas.zzb.app.console.gbcx.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/11/28.
 */
@Entity
@Table(name = "app_gbcx_a01")
public class AppGbcxA01  extends TenantEntity implements Serializable {

    private String id;
    private String xm;
    private String xb;
    private String mz;
    private String zw;
    private String csd;
    private String jg;
    private String csny;
    private String cjgzsj;
    private String rdsj;
    private String qrzxlxwjzy;
    private String zzxlxwjzy;
    private String zyjszw;
    private String xrzwsj;
    private String xrzjsj;
    private int a01Px;
    private String zpPath;

    private AppGbcxB01 appGbcxB01;

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "xm")
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Basic
    @Column(name = "xb")
    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    @Basic
    @Column(name = "mz")
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    @Basic
    @Column(name = "zw")
    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    @Basic
    @Column(name = "csd")
    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }

    @Basic
    @Column(name = "jg")
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    @Basic
    @Column(name = "csny")
    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    @Basic
    @Column(name = "cjgzsj")
    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }

    @Basic
    @Column(name = "rdsj")
    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }

    @Basic
    @Column(name = "qrzxlxwjzy")
    public String getQrzxlxwjzy() {
        return qrzxlxwjzy;
    }

    public void setQrzxlxwjzy(String qrzxlxwjzy) {
        this.qrzxlxwjzy = qrzxlxwjzy;
    }

    @Basic
    @Column(name = "zzxlxwjzy")
    public String getZzxlxwjzy() {
        return zzxlxwjzy;
    }

    public void setZzxlxwjzy(String zzxlxwjzy) {
        this.zzxlxwjzy = zzxlxwjzy;
    }

    @Basic
    @Column(name = "zyjszw")
    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }

    @Basic
    @Column(name = "xrzwsj")
    public String getXrzwsj() {
        return xrzwsj;
    }

    public void setXrzwsj(String xrzwsj) {
        this.xrzwsj = xrzwsj;
    }

    @Basic
    @Column(name = "xrzjsj")
    public String getXrzjsj() {
        return xrzjsj;
    }

    public void setXrzjsj(String xrzjsj) {
        this.xrzjsj = xrzjsj;
    }

    @Basic
    @Column(name = "a01_px")
    public int getA01Px() {
        return a01Px;
    }

    public void setA01Px(int a01Px) {
        this.a01Px = a01Px;
    }

    @Basic
    @Column(name = "zp_path")
    public String getZpPath() {
        return zpPath;
    }

    public void setZpPath(String zpPath) {
        this.zpPath = zpPath;
    }

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "b01_id")
    public AppGbcxB01 getAppGbcxB01() {
        return appGbcxB01;
    }

    public void setAppGbcxB01(AppGbcxB01 appGbcxB01) {
        this.appGbcxB01 = appGbcxB01;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppGbcxA01 that = (AppGbcxA01) o;

        if (a01Px != that.a01Px) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (xm != null ? !xm.equals(that.xm) : that.xm != null) return false;
        if (xb != null ? !xb.equals(that.xb) : that.xb != null) return false;
        if (mz != null ? !mz.equals(that.mz) : that.mz != null) return false;
        if (zw != null ? !zw.equals(that.zw) : that.zw != null) return false;
        if (csd != null ? !csd.equals(that.csd) : that.csd != null) return false;
        if (jg != null ? !jg.equals(that.jg) : that.jg != null) return false;
        if (csny != null ? !csny.equals(that.csny) : that.csny != null) return false;
        if (cjgzsj != null ? !cjgzsj.equals(that.cjgzsj) : that.cjgzsj != null) return false;
        if (rdsj != null ? !rdsj.equals(that.rdsj) : that.rdsj != null) return false;
        if (qrzxlxwjzy != null ? !qrzxlxwjzy.equals(that.qrzxlxwjzy) : that.qrzxlxwjzy != null) return false;
        if (zzxlxwjzy != null ? !zzxlxwjzy.equals(that.zzxlxwjzy) : that.zzxlxwjzy != null) return false;
        if (zyjszw != null ? !zyjszw.equals(that.zyjszw) : that.zyjszw != null) return false;
        if (xrzwsj != null ? !xrzwsj.equals(that.xrzwsj) : that.xrzwsj != null) return false;
        if (xrzjsj != null ? !xrzjsj.equals(that.xrzjsj) : that.xrzjsj != null) return false;
        if (zpPath != null ? !zpPath.equals(that.zpPath) : that.zpPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (xb != null ? xb.hashCode() : 0);
        result = 31 * result + (mz != null ? mz.hashCode() : 0);
        result = 31 * result + (zw != null ? zw.hashCode() : 0);
        result = 31 * result + (csd != null ? csd.hashCode() : 0);
        result = 31 * result + (jg != null ? jg.hashCode() : 0);
        result = 31 * result + (csny != null ? csny.hashCode() : 0);
        result = 31 * result + (cjgzsj != null ? cjgzsj.hashCode() : 0);
        result = 31 * result + (rdsj != null ? rdsj.hashCode() : 0);
        result = 31 * result + (qrzxlxwjzy != null ? qrzxlxwjzy.hashCode() : 0);
        result = 31 * result + (zzxlxwjzy != null ? zzxlxwjzy.hashCode() : 0);
        result = 31 * result + (zyjszw != null ? zyjszw.hashCode() : 0);
        result = 31 * result + (xrzwsj != null ? xrzwsj.hashCode() : 0);
        result = 31 * result + (xrzjsj != null ? xrzjsj.hashCode() : 0);
        result = 31 * result + a01Px;
        result = 31 * result + (zpPath != null ? zpPath.hashCode() : 0);
        return result;
    }
}
