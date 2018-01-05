package com.hisun.saas.zzb.app.console.gendata.entity;

import com.hisun.base.entity.BaseEntity;
import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/23.
 */
@Entity
@Table(name = "app_data_packet_content")
public class DataPacketContent extends TenantEntity implements Serializable {

    public static int SHPC_DATA=1;
    public static int GBTJ_DATA=2;
    public static int GBMC_DATA=3;
    public static int GBCX_DATA=4;
    public static int ZSCX_DATA=5;

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;

    @Column(name = "name",length = 240)
    private String name;

    @Column(name = "data_id",length = 32)
    private String dataId;


    @Column(name = "data_type")
    private int dataType;

    @Column(name = "sort")
    private int sort;

    @Column(name = "parent_id",length = 32)
    private String parent_id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "data_packet_id")
    private Gendata gendata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public Gendata getGendata() {
        return gendata;
    }

    public void setGendata(Gendata gendata) {
        this.gendata = gendata;
    }
}
