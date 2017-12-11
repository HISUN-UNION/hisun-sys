package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.ReflectionVoUtil;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_MC_A01_GBRMSPB")
public class GbMcA01gbrmspb extends TenantEntity implements Serializable{

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="app_mc_a01_id")
    private GbMcA01 gbMcA01;
    @Column(name = "xm",length = 20)
    private String xm;
    @Column(name = "xb",length = 10)
    private String xb;
    @Column(name = "csny",length = 24)
    private String csny;
    @Column(name = "nl",length = 10)
    private String nl;
    @Column(name = "zp_path",length = 128)
    private String zppath;
    @Column(name = "mz",length = 24)
    private String mz;
    @Column(name = "jg",length = 24)
    private String jg;
    @Column(name = "csd",length = 24)
    private String csd;


    @Column(name = "rdsj",length = 10)
    private String rdsj;
    @Column(name = "cjgzsj",length = 10)
    private String cjgzsj;
    @Column(name = "jkzk",length = 24)
    private String jkzk;
    @Column(name = "zyjszw",length = 60)
    private String zyjszw;
    @Column(name = "zytc",length = 60)
    private String zytc;
    @Column(name = "xl_qrz",length = 24)
    private String xlqrz;
    @Column(name = "xw_qrz",length = 24)
    private String xwqrz;
    @Column(name = "xl_zz",length = 24)
    private String xlzz;


    @Column(name = "xw_zz",length = 24)
    private String xwzz;
    @Column(name = "qrz_byyx",length = 128)
    private String  qrz_byyx;
    @Column(name = "zz_byyx",length = 128)
    private String zz_byyx;
    @Column(name = "xrzw",length = 128)
    private String xrzw;
    @Column(name = "nrzw",length = 128)
    private String nrzw;

    @Column(name = "nmzw",length = 128)
    private String nmzw;

    @Column(name = "rmly",length = 255)
    private String rmly;
    @Column(name = "cbdwyj",length = 255)
    private String cbdwyj;
    @Column(name = "spjgyj",length = 24)
    private String spjgyj;
    @Column(name = "xzjgrmyj",length = 24)
    private String xzjgrmyj;
    @Column(name = "file_path",length = 128)
    private String filepath;
    @Column(name = "file2img_path",length = 128)
    private String file2imgPath;

    @Type(type="text")
    @Column(name = "gzjl_str")
    private String gzjlStr;
    @Type(type="text")
    @Column(name = "jcqk_str")
    private String jcqkStr;
    @Type(type="text")
    @Column(name = "khjg_str")
    private String khjgStr;

    public GbMcA01gbrmspb() {

    }

    public GbMcA01gbrmspb(Map<String,String> ywJson) {

        this.xm = ywJson.get("name");
        this.xb = ywJson.get("sex");
        this.csny = ywJson.get("birthday");
        this.mz = ywJson.get("nation");
        this.jg = ywJson.get("nativeplace");
        this.csd = ywJson.get("birthplace");
        this.rdsj = ywJson.get("jointong");
        this.cjgzsj = ywJson.get("workdate");
        this.jkzk = ywJson.get("healthstate");
        this.zyjszw = ywJson.get("technology");
        this.zytc = ywJson.get("speciality");
        this.xlqrz = ywJson.get("degree");
        this.xlzz = ywJson.get("degreein");
        this.qrz_byyx = ywJson.get("school");
        this.zz_byyx = ywJson.get("schoolin");
        this.xrzw = ywJson.get("jobnow");
        this.nrzw = ywJson.get("jobnowin");
        this.nmzw = ywJson.get("jobremove");
        this.rmly = ywJson.get("reason");
        this.cbdwyj = ywJson.get("unit");
        this.spjgyj = ywJson.get("jgyj");
        this.xzjgrmyj = ywJson.get("xzjgyj");
        Object obj = ywJson.get("intro");
        if (obj instanceof String) {
            this.gzjlStr = ywJson.get("intro");
        }else{
            this.gzjlStr="";
        }
        this.jcqkStr = ywJson.get("awardpunish");
        this.khjgStr = ywJson.get("yearcheck");

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GbMcA01 getGbMcA01() {
        return gbMcA01;
    }

    public void setGbMcA01(GbMcA01 gbMcA01) {
        this.gbMcA01 = gbMcA01;
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
        this.filepath = filepath.replaceAll("\\\\", "\\\\\\\\");
    }


    public String getFile2imgPath() {
        return file2imgPath;
    }

    public void setFile2imgPath(String file2imgPath) {
        this.file2imgPath = file2imgPath.replaceAll("\\\\", "\\\\\\\\");
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

    public void addGzjl(GbMcA01gzjl gbMcA01gzjl){

    }


    public String toSqliteInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append("INSERT INTO ");
        sb.append("APP_MC_A01_GBRMSPB ");
        sb.append("(");
        sb.append("ID");
        sb.append(",APP_MC_A01_ID");
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
        sb.append("'"+ StringUtils.trimNull2Empty(id)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(gbMcA01.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xm)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xb)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(csny)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(nl)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(mz)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(jg)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(csd)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(rdsj)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(cjgzsj)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(jkzk)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zyjszw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zytc)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xlqrz)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xwqrz)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xlzz)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xwzz)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(qrz_byyx)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(zz_byyx)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xrzw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(nrzw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(nmzw)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(rmly)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(cbdwyj)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(spjgyj)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(xzjgrmyj)+"'");
        if (StringUtils.isEmpty(file2imgPath)){
            sb.append(",''");
        }else{
            String attsPath ="atts/"+file2imgPath.substring(file2imgPath.lastIndexOf(File.separator)+1);
            sb.append(",'"+attsPath+"'");

        }
        sb.append(")");
        return sb.toString();
    }


    public Map<String,String> toFieldMap(){
        Map<String,String> fieldMap = new HashMap<String,String>();
        fieldMap.put("xm",StringUtils.trimNull2Empty(this.xm));
        fieldMap.put("xb",this.xb);
        fieldMap.put("csny",this.csny);
        fieldMap.put("zp_path",this.zppath);
        fieldMap.put("mz",this.mz);
        fieldMap.put("jg",this.jg);
        fieldMap.put("csd",this.csd);
        fieldMap.put("rdsj",this.rdsj);
        fieldMap.put("cjgzsj",this.cjgzsj);
        fieldMap.put("jkzk",this.jkzk);
        fieldMap.put("zyjszw",this.zyjszw);
        fieldMap.put("zytc",this.zytc);
        fieldMap.put("xl_qrz",this.xlqrz);
        fieldMap.put("xw_qrz",this.xwqrz);
        fieldMap.put("xl_zz",this.xlzz);
        fieldMap.put("xw_zz",this.xwzz);
        fieldMap.put("qrz_byyx",this.qrz_byyx);
        fieldMap.put("zz_byyx",this.zz_byyx);
        fieldMap.put("xrzw",this.xrzw);
        fieldMap.put("nrzw",this.nrzw);
        fieldMap.put("nmzw",this.nmzw);
        fieldMap.put("rmly",this.rmly);
        fieldMap.put("cbdwyj",this.cbdwyj);
        fieldMap.put("spjgyj",this.spjgyj);
        fieldMap.put("xzjgrmyj",this.xzjgrmyj);
        fieldMap.put("gzjl_str",this.gzjlStr);
        fieldMap.put("jcqk_str",this.jcqkStr);
        fieldMap.put("khjg_str",this.khjgStr);
        return fieldMap;
    }

    public static void main(String[] args)throws Exception{
        GbMcA01gbrmspb vo = new GbMcA01gbrmspb();
        vo.setXm("zhou");
        Map<String,Object> map = ReflectionVoUtil.map(vo);
        System.out.println(map);
        GbMcA01gbrmspb newVo = (GbMcA01gbrmspb)ReflectionVoUtil.vo(GbMcA01gbrmspb.class,map);
        System.out.println(newVo);
    }
}
