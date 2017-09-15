package com.hisun.saas.zzb.app.console.sha01grzdsx.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01grzdsxDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01grzdsx;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01grzdsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01grzdsxServiceImpl extends BaseServiceImpl<Sha01grzdsx,String> implements Sha01grzdsxService {

    private Sha01grzdsxDao sha01grzdsxDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01grzdsx, String> sha01grzdsxDao) {
        this.baseDao = sha01grzdsxDao;
        this.sha01grzdsxDao = (Sha01grzdsxDao) sha01grzdsxDao;
    }

}
