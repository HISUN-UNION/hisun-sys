package com.hisun.saas.zzb.app.console.gendata.vo;

import com.aspose.slides.Collections.Generic.List;
import com.hisun.saas.zzb.app.console.gbtj.vo.GbtjVo;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;

/**
 * Created by zhouying on 2017/9/16.
 */
public class GendataVo {

    public static String SHPC_DATA="SHPC";
    public static String GBTJ_DATA="GBTJ";

    private String id;
    private String path;
    private List<ShpcVo> shpcVoList;
    private List<GbtjVo> gbtjVoList;


    public static String getShpcData() {
        return SHPC_DATA;
    }

    public static void setShpcData(String shpcData) {
        SHPC_DATA = shpcData;
    }

    public static String getGbtjData() {
        return GBTJ_DATA;
    }

    public static void setGbtjData(String gbtjData) {
        GBTJ_DATA = gbtjData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ShpcVo> getShpcVoList() {
        return shpcVoList;
    }

    public void setShpcVoList(List<ShpcVo> shpcVoList) {
        this.shpcVoList = shpcVoList;
    }

    public List<GbtjVo> getGbtjVoList() {
        return gbtjVoList;
    }

    public void setGbtjVoList(List<GbtjVo> gbtjVoList) {
        this.gbtjVoList = gbtjVoList;
    }
}
