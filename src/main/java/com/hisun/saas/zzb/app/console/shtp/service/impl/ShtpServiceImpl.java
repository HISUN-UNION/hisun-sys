package com.hisun.saas.zzb.app.console.shtp.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shtp.dao.ShtpDao;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtp;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpService;
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