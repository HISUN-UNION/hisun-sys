package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01kcclDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01kccl;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01kcclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01kcclServiceImpl extends BaseServiceImpl<Sha01kccl,String> implements Sha01kcclService {

    private Sha01kcclDao sha01kcclDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01kccl, String> sha01kcclDao) {
        this.baseDao = sha01kcclDao;
        this.sha01kcclDao = (Sha01kcclDao) sha01kcclDao;
    }

}