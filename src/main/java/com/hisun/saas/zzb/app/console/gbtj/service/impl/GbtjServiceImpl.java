package com.hisun.saas.zzb.app.console.gbtj.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gbtj.service.GbtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbtjServiceImpl extends BaseServiceImpl<Gbtj,String> implements GbtjService{

    private GbtjDao gbtjDao;

    @Autowired
    public void setBaseDao(BaseDao<Gbtj, String> gbtjDao) {
        this.baseDao = gbtjDao;
        this.gbtjDao = (GbtjDao) gbtjDao;
    }}
