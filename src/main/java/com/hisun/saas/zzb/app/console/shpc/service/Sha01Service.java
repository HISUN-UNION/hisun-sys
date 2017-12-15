package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;

import java.io.File;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01Service extends BaseService<Sha01,String> {

    public static String ATTS_PATH = File.separator+"sha01"+ File.separator;

    public void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String pcId);

    PagerVo<Sha01Vo> getSha01VoS(int pageSize, int pageNum, String shpcId, String xmQuery, String noFileQuert);

}
