package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gzjl;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01gzjlService extends BaseService<Sha01gzjl,String> {


     void saveFromWord(Sha01 sha01, Map<String, String> dataMap);

     void saveGzjls(Sha01 sha01,String gzjlStr);


}
