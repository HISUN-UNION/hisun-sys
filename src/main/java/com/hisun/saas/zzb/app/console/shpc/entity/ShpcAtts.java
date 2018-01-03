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
@Table(name = "APP_SH_PC_ATTS")
public class ShpcAtts  extends TenantEntity implements Serializable {

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="SH_PC_ID")
    private Shpc shpc;

    @Column(name = "FILE_NAME",length = 255,nullable = false)
    private String filename;

    @Column(name = "FILE_PATH",length = 255,nullable = false)
    private String filepath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shpc getShpc() {
        return shpc;
    }

    public void setShpc(Shpc shpc) {
        this.shpc = shpc;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }


}
