package com.hisun.saas.zzb.app.console.gbtj.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/15.
 */
@Entity
@Table(name = "APP_DWJG_TJ")
public class Gbtj extends TenantEntity implements Serializable{

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, unique = true, length = 32)
    private String id;
    @Column(name = "TJ_MC",length = 128)
    private String tjmc;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    @Column(name = "TJ_JSON_DATA",columnDefinition = "CLOB",nullable = true)
    private String tjjsondata;

    @Column(name = "TBLX")
    private String tblx;

    @Column(name = "TJ_PX")
    private int px;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTjmc() {
        return tjmc;
    }

    public void setTjmc(String tjmc) {
        this.tjmc = tjmc;
    }

    public String getTjjsondata() {
        return tjjsondata;
    }

    public void setTjjsondata(String tjjsondata) {
        this.tjjsondata = tjjsondata;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public String getTblx() {
        return tblx;
    }

    public void setTblx(String tblx) {
        this.tblx = tblx;
    }

    private String toInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_DWJG_TJ ");
        sb.append("(");
        sb.append("ID");
        sb.append(",TJ_MC");
        sb.append(",TJ_JSON_DATA");
        sb.append(",TBLX");
        sb.append(",TJ_PX");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.transNull(id)+"'");
        sb.append(",'"+ StringUtils.transNull(tjmc)+"'");
        sb.append(",'"+ StringUtils.transNull(tjjsondata)+"'");
        sb.append(",'"+ StringUtils.transNull(tblx)+"'");
        sb.append(","+ px+"");
        sb.append(")");
        return sb.toString();
    }
}
