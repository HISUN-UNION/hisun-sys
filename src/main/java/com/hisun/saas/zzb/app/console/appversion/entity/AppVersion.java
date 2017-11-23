package com.hisun.saas.zzb.app.console.appversion.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/11/22.
 */
@Entity
@Table(name = "app_version")
public class AppVersion extends TenantEntity implements Serializable {

    public static int ANDROID=1;
    public static int IOS=2;
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    private String id;
    @Column(name = "app_type")
    private int appType=ANDROID;//App类型,1-安卓,2-iOS
    @Column(name = "app_version_name")
    private String appVersionName;//版本名称
    @Column(name = "app_version_code")
    private String appVersionCode;//版本号build
    @Column(name = "app_name")
    private String appName;//应用名称
    @Column(name = "app_code")
    private String appCode;//应用代码
    @Column(name = "app_store_path")
    private String appStorePath;//安装包存储路径
    @Column(name = "app_md5")
    private String appMd5;

    @Column(name = "app_size")
    private String appSize;//大小(字节)


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }


    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }


    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }


    public String getAppStorePath() {
        return appStorePath;
    }

    public void setAppStorePath(String appStorePath) {
        this.appStorePath = appStorePath;
    }


    public String getAppMd5() {
        return appMd5;
    }

    public void setAppMd5(String appMd5) {
        this.appMd5 = appMd5;
    }


    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppVersion that = (AppVersion) o;

        if (appType != that.appType) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (appVersionName != null ? !appVersionName.equals(that.appVersionName) : that.appVersionName != null)
            return false;
        if (appVersionCode != null ? !appVersionCode.equals(that.appVersionCode) : that.appVersionCode != null)
            return false;
        if (appName != null ? !appName.equals(that.appName) : that.appName != null) return false;
        if (appCode != null ? !appCode.equals(that.appCode) : that.appCode != null) return false;
        if (appStorePath != null ? !appStorePath.equals(that.appStorePath) : that.appStorePath != null) return false;
        if (appMd5 != null ? !appMd5.equals(that.appMd5) : that.appMd5 != null) return false;
        if (appSize != null ? !appSize.equals(that.appSize) : that.appSize != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + appType;
        result = 31 * result + (appVersionName != null ? appVersionName.hashCode() : 0);
        result = 31 * result + (appVersionCode != null ? appVersionCode.hashCode() : 0);
        result = 31 * result + (appName != null ? appName.hashCode() : 0);
        result = 31 * result + (appCode != null ? appCode.hashCode() : 0);
        result = 31 * result + (appStorePath != null ? appStorePath.hashCode() : 0);
        result = 31 * result + (appMd5 != null ? appMd5.hashCode() : 0);
        result = 31 * result + (appSize != null ? appSize.hashCode() : 0);
        return result;
    }
}
