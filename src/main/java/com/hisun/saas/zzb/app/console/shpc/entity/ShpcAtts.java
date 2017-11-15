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

    public String toInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_PC_ATTS ");
        sb.append("(");
        sb.append("ID");
        sb.append(",SH_PC_ID");
        sb.append(",NAME");
        sb.append(",PATH");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.transNull(id)+"'");
        sb.append(",'"+ StringUtils.transNull(shpc.getId())+"'");
        sb.append(",'"+ StringUtils.transNull(filename)+"'");
        if (StringUtils.isEmpty(filepath)){
            sb.append(",''");
        }else{
            String attsPath ="atts/"+filepath.substring(filepath.lastIndexOf(File.separator)+1);
            sb.append(",'"+attsPath+"'");

        }
        sb.append(")");
        return sb.toString();
    }
}
