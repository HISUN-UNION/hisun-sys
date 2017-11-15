package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcAttsDao;
import com.hisun.saas.zzb.app.console.shpc.entity.ShpcAtts;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcAttsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/11/15.
 */
@Service
public class ShpcAttsServiceImpl extends BaseServiceImpl<ShpcAtts,String> implements ShpcAttsService{

    private ShpcAttsDao shpcAttsDao;

    @Autowired
    public void setBaseDao(BaseDao<ShpcAtts, String> shpcAttsDao) {
        this.baseDao = shpcAttsDao;
        this.shpcAttsDao = (ShpcAttsDao) shpcAttsDao;
    }


}
