package com.hisun.saas.zzb.app.console.gbmc.vo;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
public class GbMcVo{

    private String id;
    private String mc;
    private int px;
    private List<GbMcB01Vo> gbMcB01Vos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public List<GbMcB01Vo> getGbMcB01Vos() {
        return gbMcB01Vos;
    }

    public void setGbMcB01Vos(List<GbMcB01Vo> gbMcB01s) {
        this.gbMcB01Vos = gbMcB01Vos;
    }
}
