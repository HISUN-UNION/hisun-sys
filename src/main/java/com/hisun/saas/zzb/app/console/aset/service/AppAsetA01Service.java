package com.hisun.saas.zzb.app.console.aset.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;

import javax.sql.DataSource;
import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppAsetA01Service extends BaseService<AppAsetA01,String> {

    String ATTS_PATH = File.separator+"aset"+ File.separator+"a01"+File.separator;
    String APP_ATTS_PATH="aset/a01/";
    String ZP_PATH = File.separator+"aset"+ File.separator+"a01"+File.separator+"photo"+File.separator;
    String APP_ZP_PATH="aset/a01/photo/";
    String GBRMSPB_PATH = File.separator+"aset"+ File.separator+"a01"+File.separator+"gbrmspb"+File.separator;
    String GBRMSPB_DOC_TEMPLATE = File.separator+"aset"+ File.separator+"a01"+File.separator+"template"+File.separator+"gbrmspb.docx";


    Integer getMaxPx(String b01Id) ;
    void updatePx(String b01Id,int oldPx,int newPx);
    int saveFromYw(DataSource dataSource)throws Exception;
    void saveAsGbrmspb(AppAsetA01 appAsetA01)throws Exception;
    void deleteAllData() throws Exception;
    String toSqliteInsertSql(AppAsetA01 entity);

}
