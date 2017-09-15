package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01gbrmspbDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gbrmspbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01gbrmspbServiceImpl extends BaseServiceImpl<Sha01gbrmspb,String> implements Sha01gbrmspbService {

    private Sha01gbrmspbDao sha01gbrmspbDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01gbrmspb, String> sha01gbrmspbDao) {
        this.baseDao = sha01gbrmspbDao;
        this.sha01gbrmspbDao = (Sha01gbrmspbDao) sha01gbrmspbDao;
    }

}