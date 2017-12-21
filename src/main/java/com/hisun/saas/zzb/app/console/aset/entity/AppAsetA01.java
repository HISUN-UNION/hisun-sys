package com.hisun.saas.zzb.app.console.aset.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/11/28.
 */
@Entity
@Table(name = "app_aset_a01")
public class AppAsetA01 extends TenantEntity implements Serializable {
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    private String id;
    @Column(name = "xm")
    private String xm;
    @Column(name = "xb")
    private String xb;
    @Column(name = "csny")
    private String csny;
    @Column(name = "nl")
    private String nl;
    @Column(name = "mz")
    private String mz;
    @Column(name = "zw")
    private String zw;
    @Column(name = "csd")
    private String csd;
    @Column(name = "jg")
    private String jg;
    @Column(name = "jkzk")
    private String jkzk;

    @Column(name = "cjgzsj")
    private String cjgzsj;
    @Column(name = "rdsj")
    private String rdsj;
    @Column(name = "qrzxl")
    private String qrzxl;
    @Column(name = "qrzxw")
    private String qrzxw;
    @Column(name = "qrz_byyx")
    private String qrzByyx;
    @Column(name = "qrz_zy")
    private String qrzZy;
    @Column(name = "zzxl")
    private String zzxl;
    @Column(name = "zzxw")
    private String zzxw;
    @Column(name = "zz_byyx")
    private String zzByyx;
    @Column(name = "zz_zy")
    private String zzZy;


    @Column(name = "zyjszw")
    private String zyjszw;
    @Column(name = "zytc")
    private String zytc;

    @Column(name = "xrzjsj")
    private String xrzjsj;
    @Column(name = "xrzwsj")
    private String xrzwsj;

    @Column(name = "xrzw")
    private String xrzw;
    @Column(name = "nrzw")
    private String nrzw;
    @Column(name = "nmzw")
    private String nmzw;
    @Column(name = "file_path")
    private String filepath;
    @Column(name = "file2img_path")
    private String file2ImgPath;
    @Column(name = "gzjl_str")
    private String gzjlStr;
    @Column(name = "jcqk_str")
    private String jcqkStr;
    @Column(name = "khjg_str")
    private String khjgStr;


    @Column(name = "a01_px")
    private int a01Px;
    @Column(name = "zp_path")
    private String zpPath;

    @OneToMany(mappedBy = "appAsetA01" ,fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("px asc ")
    private List<AppAsetA02> appAsetA02s;

    @OneToMany(mappedBy = "appAsetA01" ,fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("shgx_px asc ")
    private List<AppAsetA36> appAsetA36s;

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

   

    public int getA01Px() {
        return a01Px;
    }

    public void setA01Px(int a01Px) {
        this.a01Px = a01Px;
    }

   

    public String getZpPath() {
        return zpPath;
    }

    public void setZpPath(String zpPath) {
        this.zpPath = zpPath;
    }

    public String getQrzxl() {
        return qrzxl;
    }

    public void setQrzxl(String qrzxl) {
        this.qrzxl = qrzxl;
    }

    public String getQrzxw() {
        return qrzxw;
    }

    public void setQrzxw(String qrzxw) {
        this.qrzxw = qrzxw;
    }

    public String getZzxl() {
        return zzxl;
    }

    public void setZzxl(String zzxl) {
        this.zzxl = zzxl;
    }

    public String getZzxw() {
        return zzxw;
    }

    public void setZzxw(String zzxw) {
        this.zzxw = zzxw;
    }

    public List<AppAsetA02> getAppAsetA02s() {
        return appAsetA02s;
    }

    public void setAppAsetA02s(List<AppAsetA02> appAsetA02s) {
        this.appAsetA02s = appAsetA02s;
    }


    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getJkzk() {
        return jkzk;
    }

    public void setJkzk(String jkzk) {
        this.jkzk = jkzk;
    }

    public String getQrzByyx() {
        return qrzByyx;
    }

    public void setQrzByyx(String qrzByyx) {
        this.qrzByyx = qrzByyx;
    }

    public String getQrzZy() {
        return qrzZy;
    }

    public void setQrzZy(String qrzZy) {
        this.qrzZy = qrzZy;
    }

    public String getZzByyx() {
        return zzByyx;
    }

    public void setZzByyx(String zzByyx) {
        this.zzByyx = zzByyx;
    }

    public String getZzZy() {
        return zzZy;
    }

    public void setZzZy(String zzZy) {
        this.zzZy = zzZy;
    }

    public String getZytc() {
        return zytc;
    }

    public void setZytc(String zytc) {
        this.zytc = zytc;
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

    public String getNmzw() {
        return nmzw;
    }

    public void setNmzw(String nmzw) {
        this.nmzw = nmzw;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFile2ImgPath() {
        return file2ImgPath;
    }

    public void setFile2ImgPath(String file2ImgPath) {
        this.file2ImgPath = file2ImgPath;
    }

    public String getGzjlStr() {
        return gzjlStr;
    }

    public void setGzjlStr(String gzjlStr) {
        this.gzjlStr = gzjlStr;
    }

    public String getJcqkStr() {
        return jcqkStr;
    }

    public void setJcqkStr(String jcqkStr) {
        this.jcqkStr = jcqkStr;
    }

    public String getKhjgStr() {
        return khjgStr;
    }

    public void setKhjgStr(String khjgStr) {
        this.khjgStr = khjgStr;
    }

    public String toSqliteInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append("insert into ");
        sb.append("app_aset_a01 ");
        sb.append("(");
        sb.append("id");
        sb.append(",xm");
        sb.append(",xb");
        sb.append(",mz");
        sb.append(",zw");
        sb.append(",csd");
        sb.append(",jg");
        sb.append(",csny");
        sb.append(",cjgzsj");
        sb.append(",rdsj");
        sb.append(",qrzxl");
        sb.append(",zyjszw");
        sb.append(",xrzwsj");
        sb.append(",xrzjsj");
        sb.append(",zp_path");
        sb.append(",zzxl");
        sb.append(",qrzxw");
        sb.append(",zzxw");
        sb.append(",nl");
        sb.append(",jkzk");
        sb.append(",zytc");
        sb.append(",qrz_byyx");
        sb.append(",zz_byyx");
        sb.append(",xrzw");
        sb.append(",nrzw");
        sb.append(",nmzw");
        sb.append(",file2img_path");
        sb.append(",qrz_zy");
        sb.append(",zz_zy");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(id)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xm)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xb)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(mz)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(csd)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(jg)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(csny)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(cjgzsj)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(rdsj)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(qrzxl)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zyjszw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xrzwsj)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xrzjsj)+"'");
        if (StringUtils.isEmpty(zpPath)){
            sb.append(",''");
        }else{
            String attsPath ="img/aset/"+zpPath.substring(zpPath.lastIndexOf(File.separator)+1);
            sb.append(",'"+attsPath+"'");

        }
        sb.append(",'"+ StringUtils.trimNull2Empty(zzxl)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(qrzxw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zzxw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(nl)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(jkzk)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zytc)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(qrzByyx)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zzByyx)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xrzw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(nrzw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(nmzw)+"'");
        if (StringUtils.isEmpty(file2ImgPath)){
            sb.append(",''");
        }else{
            String attsPath ="atts/aset/"+file2ImgPath.substring(file2ImgPath.lastIndexOf(File.separator)+1);
            sb.append(",'"+attsPath+"'");

        }
        sb.append(",'"+ StringUtils.trimNull2Empty(qrzZy)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zzZy)+"'");
        sb.append(")");
        return sb.toString();
    }


}
