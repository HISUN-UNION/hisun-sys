package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_SH_A01_DASCQK")
public class Sha01dascqk extends TenantEntity implements Serializable {

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="APP_SH_A01_ID")
    private Sha01 sha01;
    @Column(name = "PATH",length = 255)
    private String path;
    @OneToMany(mappedBy = "sha01dascqk",fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Sha01dascqktips> sha01dascqktips;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Sha01dascqktips> getSha01dascqktips() {
        return sha01dascqktips;
    }

    public void setSha01dascqktips(List<Sha01dascqktips> sha01dascqktips) {
        this.sha01dascqktips = sha01dascqktips;
    }

    public String getFile2imgPath() {
        return file2imgPath;
    }

    public void setFile2imgPath(String file2imgPath) {
        this.file2imgPath = file2imgPath;
    }


}
