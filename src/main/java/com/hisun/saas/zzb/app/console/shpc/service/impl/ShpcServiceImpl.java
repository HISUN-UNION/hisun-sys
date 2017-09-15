package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class ShpcServiceImpl extends BaseServiceImpl<Shpc,String> implements ShpcService{

    private ShpcDao shpcDao;

    @Autowired
    public void setBaseDao(BaseDao<Shpc, String> shpcDao) {
        this.baseDao = shpcDao;
        this.shpcDao = (ShpcDao) shpcDao;
    }

}
