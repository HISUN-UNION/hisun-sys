package com.hisun.saas.zzb.app.console.aset.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZsA01;
import com.hisun.util.DateUtil;
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
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    private String id;
    @Column(name = "xm")
    private String xm; //姓名
    @Column(name = "xb")
    private String xb;//性别
    @Column(name = "csny")
    private String csny;//出生年月
    @Column(name = "nl")
    private String nl;//年龄
    @Column(name = "mz")
    private String mz;//名族
    @Column(name = "zw")
    private String zw;//职务

    @Column(name = "csd")
    private String csd;//出生地
    @Column(name = "jg")
    private String jg;//籍贯
    @Column(name = "jkzk")
    private String jkzk;//健康状态

    @Column(name = "cjgzsj")
    private String cjgzsj;//参加工作时间
    @Column(name = "rdsj")
    private String rdsj;//入党时间
    @Column(name = "qrzxl")
    private String qrzxl;//全日制学历
    @Column(name = "qrzxw")
    private String qrzxw;//全日制学位
    @Column(name = "qrz_byyx")
    private String qrzByyx;//全日制毕业院校
    @Column(name = "qrz_zy")
    private String qrzZy;//全日制专业
    @Column(name = "zzxl")
    private String zzxl;//在职学历
    @Column(name = "zzxw")
    private String zzxw;//在职学位
    @Column(name = "zz_byyx")
    private String zzByyx;//在职毕业院校
    @Column(name = "zz_zy")
    private String zzZy;//在职专业


    @Column(name = "zyjszw")
    private String zyjszw;//专业技术职务
    @Column(name = "zytc")
    private String zytc;//专业特长

    @Column(name = "xrzjsj")
    private String xrzjsj;//现任职级时间
    @Column(name = "xrzwsj")
    private String xrzwsj;//现任职务时间

    @Column(name = "xrzw")
    private String xrzw;//现任职务
    @Column(name = "nrzw")
    private String nrzw;//拟任职务
    @Column(name = "nmzw")
    private String nmzw;//拟免职务
    @Column(name = "file_path")
    private String filepath;
    @Column(name = "file2img_path")
    private String file2ImgPath;
    @Column(name = "gzjl_str")
    private String gzjlStr;//工作经历
    @Column(name = "jcqk_str")
    private String jcqkStr;//奖惩情况
    @Column(name = "khjg_str")
    private String khjgStr;//考核结果


    @Column(name = "a01_px")
    private int a01Px;
    @Column(name = "zp_path")
    private String zpPath;

    @OneToMany(mappedBy = "appAsetA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("px asc ")
    private List<AppAsetA02> appAsetA02s;

    @OneToMany(mappedBy = "appAsetA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("shgx_px asc ")
    private List<AppAsetA36> appAsetA36s;

    @OneToMany(mappedBy = "appAsetA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AppZscxZsA01> appZscxZsA01s;


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


    public List<AppAsetA36> getAppAsetA36s() {
        return appAsetA36s;
    }

    public void setAppAsetA36s(List<AppAsetA36> appAsetA36s) {
        this.appAsetA36s = appAsetA36s;
    }

    public List<AppZscxZsA01> getAppZscxZsA01s() {
        return appZscxZsA01s;
    }

    public void setAppZscxZsA01s(List<AppZscxZsA01> appZscxZsA01s) {
        this.appZscxZsA01s = appZscxZsA01s;
    }
}
