package com.hisun.saas.zzb.app.console.zscx.vo;

/**
 * Created by zhouying on 2017/9/23.
 */
public class AppZscxB01Vo {


    private String id;
    private String b0101;//名称
    private int px;//排序
    private String comment;//备注
    private int dataType;//类型 0--机构 1--分类

    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getB0101() {
        return b0101;
    }

    public void setB0101(String b0101) {
        this.b0101 = b0101;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
