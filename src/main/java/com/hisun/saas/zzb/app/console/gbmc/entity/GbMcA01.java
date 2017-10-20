package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_MC_A01")
public class GbMcA01 extends TenantEntity implements Serializable{
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;

    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="b01_id")
    private GbMcB01 gbMcB01;

    @Column(name = "xm",length = 10)
    private String xm;

    @Column(name = "mz",length = 10)
    private String mz;

    @Column(name = "zw",length = 255)
    private String zw;

    @Column(name = "csd",length = 40)
    private String csd;

    @Column(name = "jg",length = 20)
    private String jg;
    @Column(name = "csny",length = 20)
    private String csny;
    @Column(name = "cjgzsj",length = 20)
    private String cjgzsj;
    @Column(name = "rdsj",length = 20)
    private String rdsj;
    @Column(name = "qrzxlxwjzy",length = 100)
    private String qrzxlxwjzy;
    @Column(name = "zzxlxwjzy",length = 100)
    private String zzxlxwjzy;
    @Column(name = "zyjszw",length = 100)
    private String zyjszw;
    @Column(name = "xrzwsj",length = 20)
    private String xrzwsj;
    @Column(name = "xrzjsj",length = 40)
    private String xrzjsj;
    @Column(name = "a01_PX")
    private int px = 0;
    @Column(name = "ZP_PATH",length = 128)
    private String zppath;

    @OneToMany(mappedBy = "gbMcA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<GbMcA01gzjl> gbMca01gzjls;

    @OneToMany(mappedBy = "gbMcA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<GbMcA01gbrmspb> gbMca01gbrmspbs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GbMcB01 getGbMcB01() {
        return gbMcB01;
    }

    public void setGbMcB01(GbMcB01 gbMcB01) {
        this.gbMcB01 = gbMcB01;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }

    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }

    public String getQrzxlxwjzy() {
        return qrzxlxwjzy;
    }

    public void setQrzxlxwjzy(String qrzxlxwjzy) {
        this.qrzxlxwjzy = qrzxlxwjzy;
    }

    public String getZzxlxwjzy() {
        return zzxlxwjzy;
    }

    public void setZzxlxwjzy(String zzxlxwjzy) {
        this.zzxlxwjzy = zzxlxwjzy;
    }

    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }

    public String getXrzwsj() {
        return xrzwsj;
    }

    public void setXrzwsj(String xrzwsj) {
        this.xrzwsj = xrzwsj;
    }

    public String getXrzjsj() {
        return xrzjsj;
    }

    public void setXrzjsj(String xrzjsj) {
        this.xrzjsj = xrzjsj;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public String getZppath() {
        return zppath;
    }

    public void setZppath(String zppath) {
        this.zppath = zppath;
    }

    public List<GbMcA01gzjl> getGbMca01gzjls() {
        return gbMca01gzjls;
    }

    public void setGbMca01gzjls(List<GbMcA01gzjl> gbMca01gzjls) {
        this.gbMca01gzjls = gbMca01gzjls;
    }

    public List<GbMcA01gbrmspb> getGbMca01gbrmspbs() {
        return gbMca01gbrmspbs;
    }

    public void setGbMca01gbrmspbs(List<GbMcA01gbrmspb> gbMca01gbrmspbs) {
        this.gbMca01gbrmspbs = gbMca01gbrmspbs;
    }
}
