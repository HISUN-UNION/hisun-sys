package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;

import java.io.File;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01gbrmspbService extends BaseService<Sha01gbrmspb,String> {

    public static String ATTS_PATH = File.separator+"sha01"+File.separator+"gbrmspb"+ File.separator;

    public String saveFromWord(Sha01gbrmspb gbrmspb , String wordsourcePath,String templatePath) throws Exception;

}
