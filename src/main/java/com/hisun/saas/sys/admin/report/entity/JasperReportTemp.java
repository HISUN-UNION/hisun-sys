package com.hisun.saas.sys.admin.report.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jamin30 on 2015/10/22.
 */
@Entity
@Table(name="SYS_REPORT_TEMPLATE")
public class JasperReportTemp  extends TombstoneEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    private String id;

    /**
     * 报表模板名称
     */
    @Column(name="report_temp_name")
    private String reportTempName;

    /**
     * 报表模板路径
     */
    @Column(name="report_temp_path")
    private String reportTempPath;

    /**
     * 报表模板类型
     */
    @Column(name="report_temp_type")
    private String reportTempType;

    public String getReportTempDesc() {
        return reportTempDesc;
    }

    public void setReportTempDesc(String reportTempDesc) {
        this.reportTempDesc = reportTempDesc;
    }

    public String getReportTempType() {
        return reportTempType;
    }

    public void setReportTempType(String reportTempType) {
        this.reportTempType = reportTempType;
    }

    /**
     * 报表模板描述
     */
    @Column(name="report_temp_desc")

    private String reportTempDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReportTempName() {
        return reportTempName;
    }

    public void setReportTempName(String reportTempName) {
        this.reportTempName = reportTempName;
    }

    public String getReportTempPath() {
        return reportTempPath;
    }

    public void setReportTempPath(String reportTempPath) {
        this.reportTempPath = reportTempPath;
    }

    /**
     * 模版标示
     */
    @Column(name="ident_code")
    private String identCode;

    public String getIdentCode() {
        return identCode;
    }

    public void setIdentCode(String identCode) {
        this.identCode = identCode;
    }

    @Column(name="server_id")
    private String serverId;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
