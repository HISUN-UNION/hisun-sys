package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01kccl;

import java.io.File;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01kcclService extends BaseService<Sha01kccl,String> {

    public static String ATTS_PATH = File.separator+"sha01"+File.separator+"kccl"+ File.separator;;
}
