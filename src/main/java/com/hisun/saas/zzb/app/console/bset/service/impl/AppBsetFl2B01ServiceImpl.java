package com.hisun.saas.zzb.app.console.bset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFl2B01Dao;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl2B01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFl2B01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppBsetFl2B01ServiceImpl extends BaseServiceImpl<AppBsetFl2B01,String> implements AppBsetFl2B01Service {

    private AppBsetFl2B01Dao appBsetFl2B01Dao;

    @Autowired
    public void setBaseDao(BaseDao<AppBsetFl2B01, String> appBsetFl2B01Dao) {
        this.baseDao = appBsetFl2B01Dao;
        this.appBsetFl2B01Dao = (AppBsetFl2B01Dao) appBsetFl2B01Dao;
    }

}
