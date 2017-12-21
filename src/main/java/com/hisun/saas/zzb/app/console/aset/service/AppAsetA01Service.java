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
    String ZP_PATH = File.separator+"aset"+ File.separator+"a01"+File.separator+"photo"+File.separator;
    String GBRMSPB_PATH = File.separator+"aset"+ File.separator+"a01"+File.separator+"gbrmspb"+File.separator;
    String GBRMSPB_DOC_TEMPLATE = GBRMSPB_PATH+"gbrmspb.docx";


    Integer getMaxPx(String b01Id) ;
    void updatePx(String b01Id,int oldPx,int newPx);

    int saveAsetA01FromYw(DataSource dataSource)throws Exception;

}
