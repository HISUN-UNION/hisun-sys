package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01dascqkDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01dascqk;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01dascqkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01dascqkServiceImpl extends BaseServiceImpl<Sha01dascqk,String> implements Sha01dascqkService {

    private Sha01dascqkDao sha01dascqkDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01dascqk, String> sha01dascqkDao) {
        this.baseDao = sha01dascqkDao;
        this.sha01dascqkDao = (Sha01dascqkDao) sha01dascqkDao;
    }

}