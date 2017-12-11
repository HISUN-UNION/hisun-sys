package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_MC_A01")
public class GbMcA01 extends TenantEntity implements Serializable {
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, unique = true, length = 32)
    private String id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "b01_id")
    private GbMcB01 gbMcB01;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "mc_id")
    private GbMc gbMc;

    @Column(name = "xm", length = 10)
    private String xm;

    @Column(name = "mz", length = 10)
    private String mz;

    @Column(name = "zw", length = 255)
    private String zw;

    @Column(name = "csd", length = 40)
    private String csd;

    @Column(name = "jg", length = 20)
    private String jg;
    @Column(name = "csny", length = 20)
    private String csny;
    @Column(name = "cjgzsj", length = 20)
    private String cjgzsj;
    @Column(name = "rdsj", length = 20)
    private String rdsj;
    @Column(name = "qrzxlxwjzy", length = 100)
    private String qrzxlxwjzy;
    @Column(name = "zzxlxwjzy", length = 100)
    private String zzxlxwjzy;
    @Column(name = "zyjszw", length = 100)
    private String zyjszw;
    @Column(name = "xrzwsj", length = 20)
    private String xrzwsj;
    @Column(name = "xrzjsj", length = 40)
    private String xrzjsj;
    @Column(name = "a01_PX")
    private int px = 0;
    @Column(name = "ZP_PATH", length = 128)
    private String zppath;

    @OneToMany(mappedBy = "gbMcA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("gzjl_px asc")
    private List<GbMcA01gzjl> gbMca01gzjls;

    @OneToMany(mappedBy = "gbMcA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<GbMcA01gbrmspb> gbMca01gbrmspbs;

    @OneToMany(mappedBy = "gbMcA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("shgx_px asc")
    private List<GbMcA01shgx> gbMcA01shgxes;

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

    public GbMc getGbMc() {
        return gbMc;
    }

    public void setGbMc(GbMc gbMc) {
        this.gbMc = gbMc;
    }

    public List<GbMcA01shgx> getGbMcA01shgxes() {
        return gbMcA01shgxes;
    }

    public void setGbMcA01shgxes(List<GbMcA01shgx> gbMcA01shgxes) {
        this.gbMcA01shgxes = gbMcA01shgxes;
    }

    public void addGbrmspb(GbMcA01gbrmspb gbMcA01gbrmspb) {
        if (this.gbMca01gbrmspbs == null) {
            this.gbMca01gbrmspbs = new ArrayList<GbMcA01gbrmspb>();
        }
        gbMcA01gbrmspb.setGbMcA01(this);
        this.gbMca01gbrmspbs.add(gbMcA01gbrmspb);
    }

    public void addGzjl(GbMcA01gzjl gbMcA01gzjl) {
        if (this.gbMca01gzjls == null) {
            this.gbMca01gzjls = new ArrayList<GbMcA01gzjl>();
        }
        gbMcA01gzjl.setGbMcA01(this);
        this.gbMca01gzjls.add(gbMcA01gzjl);
    }

    public void addShgx(GbMcA01shgx gbMcA01shgx){
        if(this.gbMcA01shgxes==null){
            this.gbMcA01shgxes = new ArrayList<GbMcA01shgx>();
        }
        gbMcA01shgx.setGbMcA01(this);
        this.gbMcA01shgxes.add(gbMcA01shgx);
    }

    public String toSqliteInsertSql() {
        StringBuffer sb = new StringBuffer("");
        sb.append(" insert into ");
        sb.append(" app_mc_a01 ");
        sb.append("(");
        sb.append("id");
        sb.append(",b01_id");
        sb.append(",xm");
        sb.append(",mz");
        sb.append(",zw");
        sb.append(",csd");
        sb.append(",jg");
        sb.append(",csny");
        sb.append(",cjgzsj");
        sb.append(",rdsj");
        sb.append(",qrzxlxwjzy");
        sb.append(",zzxlxwjzy");
        sb.append(",zyjszw");
        sb.append(",xrzwsj");
        sb.append(",xrzjsj");
        sb.append(",zp_path");
        sb.append(",a01_px");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'" + StringUtils.trimNull2Empty(id) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(gbMcB01.getId()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(xm) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(mz) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(zw) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(csd) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(jg) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(csny) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(cjgzsj) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(rdsj) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(qrzxlxwjzy) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(zzxlxwjzy) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(zyjszw) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(xrzwsj) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(xrzjsj) + "'");
        if (StringUtils.isEmpty(zppath)) {
            sb.append(",''");
        } else {
            String filepath = "img/" + zppath.substring(zppath.lastIndexOf(File.separator) + 1);
            sb.append(",'" + filepath + "'");

        }
        sb.append("," + px + "");
        sb.append(")");
        return sb.toString();
    }
}
