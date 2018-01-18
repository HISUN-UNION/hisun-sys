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
    String ZP_PATH = ATTS_PATH+"photo"+File.separator;
    String APP_ZP_PATH=APP_ATTS_PATH+"photo/";
    String GBRMSPB_PATH = ATTS_PATH+"gbrmspb"+File.separator;
    String APP_GBRMSPB_PATH = APP_ATTS_PATH+"gbrmspb/";
    String GBRMSPB_DOC_TEMPLATE = ATTS_PATH+"template"+File.separator+"gbrmspb.docx";


    Integer getMaxPx(String b01Id) ;
    void updatePx(String b01Id,int oldPx,int newPx);
    int saveFromYw(DataSource dataSource)throws Exception;
    void saveAsGbrmspb(AppAsetA01 appAsetA01)throws Exception;
    void deleteAllData() throws Exception;
    String toSqliteInsertSql(AppAsetA01 entity);


}
