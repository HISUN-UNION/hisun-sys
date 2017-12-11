package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name="APP_MC")
public class GbMc extends TenantEntity implements Serializable{

    public static int YML=0;//有目录
    public static int WML=1;//无目录

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @Column(name = "mc",length = 255)
    private String mc;
    @Column(name = "px")
    private int px;
    @Column(name = "is_ml")
    private int isMl = YML;//是否存在目录

    @OneToMany(mappedBy="gbMc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<GbMcB01> gbMcB01s;

    @OneToMany(mappedBy = "gbMcB01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<GbMcA01> gbMcA01s;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public List<GbMcB01> getGbMcB01s() {
        return gbMcB01s;
    }

    public void setGbMcB01s(List<GbMcB01> gbMcB01s) {
        this.gbMcB01s = gbMcB01s;
    }

    public int getIsMl() {
        return isMl;
    }

    public void setIsMl(int isMl) {
        this.isMl = isMl;
    }

    public List<GbMcA01> getGbMcA01s() {
        return gbMcA01s;
    }

    public void setGbMcA01s(List<GbMcA01> gbMcA01s) {
        this.gbMcA01s = gbMcA01s;
    }

    public String toSqliteInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_mc ");
        sb.append("(");
        sb.append("id");
        sb.append(",mc");
        sb.append(",px");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(id)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(mc)+"'");
        sb.append(","+px);
        sb.append(")");
        return sb.toString();
    }


}
