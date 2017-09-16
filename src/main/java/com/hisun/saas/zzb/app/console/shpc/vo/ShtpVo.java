package com.hisun.saas.zzb.app.console.shpc.vo;

import com.hisun.saas.sys.tenant.tenant.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhouying on 2017/9/15.
 */
public class ShtpVo{
    private String id;
    private ShpcVo shpc;
    private String tpq_bh;
    private String tpr_id;
    private String tpr_xm;
    private String tp_sj;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTpq_bh() {
        return tpq_bh;
    }

    public void setTpq_bh(String tpq_bh) {
        this.tpq_bh = tpq_bh;
    }

    public String getTpr_id() {
        return tpr_id;
    }

    public void setTpr_id(String tpr_id) {
        this.tpr_id = tpr_id;
    }

    public String getTpr_xm() {
        return tpr_xm;
    }

    public void setTpr_xm(String tpr_xm) {
        this.tpr_xm = tpr_xm;
    }

    public String getTp_sj() {
        return tp_sj;
    }

    public void setTp_sj(String tp_sj) {
        this.tp_sj = tp_sj;
    }

    public ShpcVo getShpc() {
        return shpc;
    }

    public void setShpc(ShpcVo shpc) {
        this.shpc = shpc;
    }
}
