package com.hisun.saas.zzb.app.console.shpc.vo;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
public class ShpcAttsVo extends TenantEntity implements Serializable {

    private String id;
    private String shpcId;
    private String filename;
    private String filepath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getShpcId() {
        return shpcId;
    }

    public void setShpcId(String shpcId) {
        this.shpcId = shpcId;
    }
}
