package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.ShtpDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Shtp;
import com.hisun.saas.zzb.app.console.shpc.service.ShtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class ShtpServiceImpl extends BaseServiceImpl<Shtp,String> implements ShtpService {

    private ShtpDao shtpDao;

    @Autowired
    public void setBaseDao(BaseDao<Shtp, String> shtpDao) {
        this.baseDao = shtpDao;
        this.shtpDao = (ShtpDao) shtpDao;
    }

}