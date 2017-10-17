package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbMcServiceImpl extends BaseServiceImpl<GbMc,String> implements GbMcService {

    private GbMcDao gbMcDao;

    @Autowired
    public void setBaseDao(BaseDao<GbMc, String> gbMcDao) {
        this.baseDao = gbMcDao;
        this.gbMcDao = (GbMcDao) gbMcDao;
    }}
