package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_SH_A01")
public class Sha01 extends TenantEntity implements Serializable {
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, unique = true, length = 32)
    private String id;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "APP_SH_PC_ID")
    private Shpc shpc;
    @Column(name = "XM", length = 20)
    private String xm;
    @Column(name = "XB", length = 10)
    private String xb;
    @Column(name = "MZ", length = 20)
    private String mz;
    @Column(name = "JG", length = 20)
    private String jg;
    @Column(name = "CSNY", length = 24)
    private String csny;
    @Column(name = "CJGZSJ", length = 24)
    private String cjgzsj;
    @Column(name = "RDSJ", length = 24)
    private String rdsj;
    @Column(name = "WHCD", length = 40)
    private String whcd;
    @Column(name = "RXJBSJ", length = 24)
    private String rxjbsj;
    @Column(name = "MZTJQK", length = 80)
    private String mztjqk;
    @Column(name = "YWFPJL", length = 10)
    private String ywfpjl;
    @Column(name = "XGZDWJZW", length = 10)
    private String xgzdwjzw;
    @Column(name = "NTZPBYJ", length = 24)
    private String ntzpbyj;
    @Column(name = "SHYJ", length = 24)
    private String shyj;
    @Column(name = "A01_PX")
    private int px = 0;

    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01dascqk> dascqks;
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01gbrmspb> gbrmspbs;
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01grzdsx> grzdsxes;
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01gzjl> gzjls;
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01jc> jcs;
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01kccl> kccls;
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01ndkh> ndkhs;
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01shgx> shgxes;

    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Shtpsj> shtpsjs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shpc getShpc() {
        return shpc;
    }

    public void setShpc(Shpc shpc) {
        this.shpc = shpc;
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

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }

    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
    }

    public String getRxjbsj() {
        return rxjbsj;
    }

    public void setRxjbsj(String rxjbsj) {
        this.rxjbsj = rxjbsj;
    }

    public String getMztjqk() {
        return mztjqk;
    }

    public void setMztjqk(String mztjqk) {
        this.mztjqk = mztjqk;
    }

    public String getYwfpjl() {
        return ywfpjl;
    }

    public void setYwfpjl(String ywfpjl) {
        this.ywfpjl = ywfpjl;
    }

    public String getNtzpbyj() {
        return ntzpbyj;
    }

    public void setNtzpbyj(String ntzpbyj) {
        this.ntzpbyj = ntzpbyj;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public List<Sha01dascqk> getDascqks() {
        return dascqks;
    }

    public void setDascqks(List<Sha01dascqk> dascqks) {
        this.dascqks = dascqks;
    }

    public List<Sha01gzjl> getGzjls() {
        return gzjls;
    }

    public void setGzjls(List<Sha01gzjl> gzjls) {
        this.gzjls = gzjls;
    }

    public List<Sha01jc> getJcs() {
        return jcs;
    }

    public void setJcs(List<Sha01jc> jcs) {
        this.jcs = jcs;
    }

    public List<Sha01kccl> getKccls() {
        return kccls;
    }

    public void setKccls(List<Sha01kccl> kccls) {
        this.kccls = kccls;
    }

    public List<Sha01ndkh> getNdkhs() {
        return ndkhs;
    }

    public void setNdkhs(List<Sha01ndkh> ndkhs) {
        this.ndkhs = ndkhs;
    }

    public List<Sha01shgx> getShgxes() {
        return shgxes;
    }

    public void setShgxes(List<Sha01shgx> shgxes) {
        this.shgxes = shgxes;
    }

    public List<Sha01gbrmspb> getGbrmspbs() {
        return gbrmspbs;
    }

    public void setGbrmspbs(List<Sha01gbrmspb> gbrmspbs) {
        this.gbrmspbs = gbrmspbs;
    }

    public List<Sha01grzdsx> getGrzdsxes() {
        return grzdsxes;
    }

    public void setGrzdsxes(List<Sha01grzdsx> grzdsxes) {
        this.grzdsxes = grzdsxes;
    }


    public List<Shtpsj> getShtpsjs() {
        return shtpsjs;
    }

    public void setShtpsjs(List<Shtpsj> shtpsjs) {
        this.shtpsjs = shtpsjs;
    }

    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }

    public String getXgzdwjzw() {
        return xgzdwjzw;
    }

    public void setXgzdwjzw(String xgzdwjzw) {
        this.xgzdwjzw = xgzdwjzw;
    }

    public String toInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_A01 ");
        sb.append("(");
        sb.append("ID");
        sb.append("APP_SH_PC_ID");
        sb.append(",XM");
        sb.append(",XB");
        sb.append(",MZ");
        sb.append(",JG");
        sb.append(",CSNY");
        sb.append(",CJGZSJ");
        sb.append(",RDSJ");
        sb.append(",WHCD");
        sb.append(",RXJBSJ");
        sb.append(",MZTJQK");
        sb.append(",YWFPJL");
        sb.append(",XGZDWJZW");
        sb.append(",NTZPBYJ");
        sb.append(",SHYJ");
        sb.append(",A01_PX");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.transNull(id)+"'");
        sb.append(",'"+ StringUtils.transNull(shpc.getId())+"'");
        sb.append(",'"+ StringUtils.transNull(xm)+"'");
        sb.append(",'"+ StringUtils.transNull(xb)+"'");
        sb.append(",'"+ StringUtils.transNull(mz)+"'");
        sb.append(",'"+ StringUtils.transNull(jg)+"'");
        sb.append(",'"+ StringUtils.transNull(csny)+"'");
        sb.append(",'"+ StringUtils.transNull(cjgzsj)+"'");
        sb.append(",'"+ StringUtils.transNull(rdsj)+"'");
        sb.append(",'"+ StringUtils.transNull(whcd)+"'");
        sb.append(",'"+ StringUtils.transNull(rxjbsj)+"'");
        sb.append(",'"+ StringUtils.transNull(mztjqk)+"'");
        sb.append(",'"+ StringUtils.transNull(ywfpjl)+"'");
        sb.append(",'"+ StringUtils.transNull(xgzdwjzw)+"'");
        sb.append(",'"+ StringUtils.transNull(ntzpbyj)+"'");
        sb.append(",'"+ StringUtils.transNull(shyj)+"'");
        sb.append(","+ px+"");

        sb.append(")");
        return sb.toString();
    }
}
