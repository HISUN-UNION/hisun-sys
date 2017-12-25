package com.hisun.saas.zzb.app.console.bset.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/12/16.
 */
@Entity
@Table(name = "app_bset_fl")
public class AppBsetFl extends TenantEntity implements Serializable {

    public static int DISPLAY=0;
    public static int HIDDEN=1;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    private String id;
    @Column(name = "fl")
    private String fl;
    @Column(name = "px")
    private int px;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private AppBsetFl parentFl;
    @OneToMany(mappedBy = "parentFl", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AppBsetFl> children;
    @OneToMany(mappedBy = "appBsetFl", fetch = FetchType.LAZY)
    @OrderBy("px asc ")
    private List<AppBsetFl2B01> appBsetFl2B01s;
    @Column(name = "is_hidden")
    private int isHidden =DISPLAY;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public AppBsetFl getParentFl() {
        return parentFl;
    }

    public void setParentFl(AppBsetFl parentFl) {
        this.parentFl = parentFl;
    }

    public List<AppBsetFl> getChildren() {
        return children;
    }

    public void setChildren(List<AppBsetFl> children) {
        this.children = children;
    }

    public List<AppBsetFl2B01> getAppBsetFl2B01s() {
        return appBsetFl2B01s;
    }

    public void setAppBsetFl2B01s(List<AppBsetFl2B01> appBsetFl2B01s) {
        this.appBsetFl2B01s = appBsetFl2B01s;
    }

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }

    public String toSqliteInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_bset_fl ");
        sb.append("(");
        sb.append("id");
        sb.append(",fl");
        if(parentFl!=null) {
            sb.append(",parent_id");
        }
        sb.append(",px");
        sb.append(",is_hidden");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(id)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(fl)+"'");
        if(parentFl!=null){
            sb.append(",'" + StringUtils.trimNull2Empty(parentFl.getId()) + "'");
        }
        sb.append(","+px);
        sb.append(","+isHidden);
        sb.append(")");
        return sb.toString();
    }
}
