package com.hisun.saas.zzb.app.console.gbmc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gbrmspb;

import java.io.File;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface GbMcA01gbrmspbService extends BaseService<GbMcA01gbrmspb,String> {

    public static String ATTS_PATH = File.separator+"mca01"+File.separator+"gbrmspb"+ File.separator;
    public static String GBRMSPB_DOC_TEMPLATE = File.separator+"mca01"+File.separator+"gbrmspb"+ File.separator+"gbrmspb.docx";


    public String saveFromWord(GbMcA01gbrmspb gbrmspb, String wordsourcePath, String templatePath) throws Exception;

}
