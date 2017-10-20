package com.hisun.saas.zzb.app.console.gbmc.vo;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;

import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
public class GbMcA01gzjlVo extends TenantEntity implements Serializable {

    private String id;
    private GbMcA01Vo gbMcA01Vo;
    private String csj;
    private String zsj;
    private String jlsm;
    private int px;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GbMcA01Vo getGbMcA01Vo() {
        return gbMcA01Vo;
    }

    public void setGbMcA01Vo(GbMcA01Vo gbMcA01Vo) {
        this.gbMcA01Vo = gbMcA01Vo;
    }

    public String getCsj() {
        return csj;
    }

    public void setCsj(String csj) {
        this.csj = csj;
    }

    public String getZsj() {
        return zsj;
    }

    public void setZsj(String zsj) {
        this.zsj = zsj;
    }

    public String getJlsm() {
        return jlsm;
    }

    public void setJlsm(String jlsm) {
        this.jlsm = jlsm;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
}
