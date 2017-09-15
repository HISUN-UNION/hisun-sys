package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01dascqktipsDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01dascqktips;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01dascqktipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01dascqktipsServiceImpl extends BaseServiceImpl<Sha01dascqktips, String> implements Sha01dascqktipsService {

    private Sha01dascqktipsDao sha01dascqktipsDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01dascqktips, String> sha01dascqktipsDao) {
        this.baseDao = sha01dascqktipsDao;
        this.sha01dascqktipsDao = (Sha01dascqktipsDao) sha01dascqktipsDao;
    }

}