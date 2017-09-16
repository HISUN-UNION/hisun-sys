package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_SH_A01_GRZDSX")
public class Sha01grzdsx extends TenantEntity implements Serializable {

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

    public String toInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_A01_GRZDSX ");
        sb.append("(");
        sb.append("ID");
        sb.append(",APP_SH_A01_ID");
        sb.append(",PATH");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.transNull(id)+"'");
        sb.append(",'"+ StringUtils.transNull(sha01.getId())+"'");
        if (StringUtils.isEmpty(path)){
            sb.append(",''");
        }else{
            String filepath ="atts/"+path.substring(path.lastIndexOf(File.separator)+1);
            sb.append("'"+filepath+"'");

        }
        sb.append(")");
        return sb.toString();
    }

}
