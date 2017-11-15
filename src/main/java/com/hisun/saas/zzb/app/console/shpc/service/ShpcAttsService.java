package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.ShpcAtts;

import java.io.File;

/**
 * Created by zhouying on 2017/11/15.
 */
public interface ShpcAttsService extends BaseService<ShpcAtts,String> {
    public static String ATTS_PATH = File.separator+"shpcAtts"+ File.separator;
}
