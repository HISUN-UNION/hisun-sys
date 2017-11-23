package com.hisun.saas.zzb.app.console.appclient.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/11/22.
 */
@Entity
@Table(name = "app_client")
public class AppClient extends TenantEntity implements Serializable {

    public static int ON=1;
    public static int OFF=2;

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;

    @Column(name = "identification")
    private String identification;//终端标识

    @Column(name = "status")
    private int status=ON;//1-正常,2-停用


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppClient that = (AppClient) o;

        if (status != that.status) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (identification != null ? !identification.equals(that.identification) : that.identification != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (identification != null ? identification.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
