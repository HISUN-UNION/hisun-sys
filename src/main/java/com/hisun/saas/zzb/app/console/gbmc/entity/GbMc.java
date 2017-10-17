package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name="APP_MC")
public class GbMc extends TenantEntity implements Serializable{

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @Column(name = "MC",length = 255)
    private String mc;
    @Column(name = "PX")
    private int px;
    @OneToMany(mappedBy="gbMc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<GbMcB01> gbMcB01s;

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
}
