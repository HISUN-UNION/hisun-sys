package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
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
    @Column(name = "ZP_PATH",length = 128)
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
    @Column(name = "XL_ZZ",length = 24)
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

    @Column(name = "NMZW",length = 128)
    private String nmzw;

    @Column(name = "RMLY",length = 255)
    private String rmly;
    @Column(name = "CBDWYJ",length = 255)
    private String cbdwyj;
    @Column(name = "SPJGYJ",length = 24)
    private String spjgyj;
    @Column(name = "XZJGRMYJ",length = 24)
    private String xzjgrmyj;
    @Column(name = "file_path",length = 128)
    private String filepath;
    @Column(name = "FILE2IMG_PATH",length = 128)
    private String file2imgPath;


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
        //在Windows环境下,采用原生SQL操作数据库,注意文件路径
        this.filepath = filepath.replaceAll("\\\\", "\\\\\\\\");
    }


    public String getFile2imgPath() {
        return file2imgPath;
    }

    public void setFile2imgPath(String file2imgPath) {
        this.file2imgPath = file2imgPath.replaceAll("\\\\", "\\\\\\\\");
    }

    public String toInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_A01_GBRMSPB ");
        sb.append("(");
        sb.append("ID");
        sb.append(",APP_SH_A01_ID");
        sb.append(",XM");
        sb.append(",XB");
        sb.append(",CSNY");
        sb.append(",NL");
        sb.append(",MZ");
        sb.append(",JG");
        sb.append(",CSD");
        sb.append(",RDSJ");
        sb.append(",CJGZSJ");
        sb.append(",JKZK");
        sb.append(",ZYJSZW");
        sb.append(",ZYTC");
        sb.append(",XL_QRZ");
        sb.append(",XW_QRZ");
        sb.append(",XL_ZZ");
        sb.append(",XW_ZZ");
        sb.append(",QRZ_BYYX");
        sb.append(",ZZ_BYYX");
        sb.append(",XRZW");
        sb.append(",NRZW");
        sb.append(",NMZW");
        sb.append(",RMLY");
        sb.append(",CBDWYJ");
        sb.append(",SPJGYJ");
        sb.append(",XZJGRMYJ");
        sb.append(",PATH");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.transNull(id)+"'");
        sb.append(",'"+ StringUtils.transNull(sha01.getId())+"'");
        sb.append(",'"+ StringUtils.transNull(xm)+"'");
        sb.append(",'"+ StringUtils.transNull(xb)+"'");
        sb.append(",'"+ StringUtils.transNull(csny)+"'");
        sb.append(",'"+ StringUtils.transNull(nl)+"'");

        sb.append(",'"+ StringUtils.transNull(mz)+"'");
        sb.append(",'"+ StringUtils.transNull(jg)+"'");
        sb.append(",'"+ StringUtils.transNull(csd)+"'");
        sb.append(",'"+ StringUtils.transNull(rdsj)+"'");
        sb.append(",'"+ StringUtils.transNull(cjgzsj)+"'");

        sb.append(",'"+ StringUtils.transNull(jkzk)+"'");
        sb.append(",'"+ StringUtils.transNull(zyjszw)+"'");
        sb.append(",'"+ StringUtils.transNull(zytc)+"'");
        sb.append(",'"+ StringUtils.transNull(xlqrz)+"'");
        sb.append(",'"+ StringUtils.transNull(xwqrz)+"'");
        sb.append(",'"+ StringUtils.transNull(xlzz)+"'");
        sb.append(",'"+ StringUtils.transNull(xwzz)+"'");
        sb.append(",'"+ StringUtils.transNull(qrz_byyx)+"'");


        sb.append(",'"+ StringUtils.transNull(zz_byyx)+"'");
        sb.append(",'"+ StringUtils.transNull(xrzw)+"'");
        sb.append(",'"+ StringUtils.transNull(nrzw)+"'");
        sb.append(",'"+ StringUtils.transNull(nmzw)+"'");

        sb.append(",'"+ StringUtils.transNull(rmly)+"'");
        sb.append(",'"+ StringUtils.transNull(cbdwyj)+"'");
        sb.append(",'"+ StringUtils.transNull(spjgyj)+"'");
        sb.append(",'"+ StringUtils.transNull(xzjgrmyj)+"'");
        if (StringUtils.isEmpty(file2imgPath)){
            sb.append(",''");
        }else{
            String attsPath ="atts/"+file2imgPath.substring(file2imgPath.lastIndexOf(File.separator)+1);
            sb.append(",'"+attsPath+"'");

        }
        sb.append(")");
        return sb.toString();
    }


}
