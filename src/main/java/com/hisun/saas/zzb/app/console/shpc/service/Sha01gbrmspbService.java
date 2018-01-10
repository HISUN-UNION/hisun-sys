package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;

import java.io.File;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01gbrmspbService extends BaseService<Sha01gbrmspb,String> {

    String ATTS_PATH = Sha01Service.ATTS_PATH+"gbrmspb"+ File.separator;
    String APP_ATTS_PATH= Sha01Service.APP_ATTS_PATH+"gbrmspb/";
    String GBRMSPB_TEMPLATE=ATTS_PATH+"gbrmspb.docx";

    String saveFromWord(String sha01Id,String wordsourcePath,String templatePath) throws Exception;
    String toSqliteInsertSql(Sha01gbrmspb entity);
}
