package com.hisun.saas.zzb.app.console.shpc.vo;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
public class Sha01Vo {
    private String id;
    private ShpcVo shpc;
    private String xm;
    private String xb;
    private String mz;
    private String jg;
    private String csny;
    private String rdsj;
    private String whcd;
    private String rxjbsj;
    private String mztjqk;
    private String ywfpjl;
    private String ntzpbyj;
    private String shyj;
    private int px = 0;

    private List<Sha01dascqkVo> dascqks;
    private List<Sha01gbrmspbVo> gbrmspbs;
    private List<Sha01grzdsxVo> grzdsxes;
    private List<Sha01gzjlVo> gzjls;
    private List<Sha01jcVo> jcs;
    private List<Sha01kcclVo> kccls;
    private List<Sha01ndkhVo> ndkhs;
    private List<Sha01shgxVo> shgxes;

    private List<ShtpsjVo> shtpsjs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShpcVo getShpc() {
        return shpc;
    }

    public void setShpc(ShpcVo shpc) {
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

    public List<Sha01dascqkVo> getDascqks() {
        return dascqks;
    }

    public void setDascqks(List<Sha01dascqkVo> dascqks) {
        this.dascqks = dascqks;
    }

    public List<Sha01gzjlVo> getGzjls() {
        return gzjls;
    }

    public void setGzjls(List<Sha01gzjlVo> gzjls) {
        this.gzjls = gzjls;
    }

    public List<Sha01jcVo> getJcs() {
        return jcs;
    }

    public void setJcs(List<Sha01jcVo> jcs) {
        this.jcs = jcs;
    }

    public List<Sha01kcclVo> getKccls() {
        return kccls;
    }

    public void setKccls(List<Sha01kcclVo> kccls) {
        this.kccls = kccls;
    }

    public List<Sha01ndkhVo> getNdkhs() {
        return ndkhs;
    }

    public void setNdkhs(List<Sha01ndkhVo> ndkhs) {
        this.ndkhs = ndkhs;
    }

    public List<Sha01shgxVo> getShgxes() {
        return shgxes;
    }

    public void setShgxes(List<Sha01shgxVo> shgxes) {
        this.shgxes = shgxes;
    }

    public List<Sha01gbrmspbVo> getGbrmspbs() {
        return gbrmspbs;
    }

    public void setGbrmspbs(List<Sha01gbrmspbVo> gbrmspbs) {
        this.gbrmspbs = gbrmspbs;
    }

    public List<Sha01grzdsxVo> getGrzdsxes() {
        return grzdsxes;
    }

    public void setGrzdsxes(List<Sha01grzdsxVo> grzdsxes) {
        this.grzdsxes = grzdsxes;
    }


    public List<ShtpsjVo> getShtpsjs() {
        return shtpsjs;
    }

    public void setShtpsjs(List<ShtpsjVo> shtpsjs) {
        this.shtpsjs = shtpsjs;
    }
}