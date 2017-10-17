package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcB01Dao;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcB01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbMcB01ServiceImpl extends BaseServiceImpl<GbMcB01,String> implements GbMcB01Service {

    private GbMcB01Dao gbMcB01Dao;

    @Autowired
    public void setBaseDao(BaseDao<GbMcB01, String> gbMcB01Dao) {
        this.baseDao = gbMcB01Dao;
        this.gbMcB01Dao = (GbMcB01Dao) gbMcB01Dao;
    }}
