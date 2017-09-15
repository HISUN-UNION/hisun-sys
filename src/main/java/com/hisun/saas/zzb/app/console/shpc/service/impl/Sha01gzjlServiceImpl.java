package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01gzjlDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gzjl;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gzjlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01gzjlServiceImpl extends BaseServiceImpl<Sha01gzjl,String> implements Sha01gzjlService {

    private Sha01gzjlDao sha01gzjlDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01gzjl, String> sha01gzjlDao) {
        this.baseDao = sha01gzjlDao;
        this.sha01gzjlDao = (Sha01gzjlDao) sha01gzjlDao;
    }

}