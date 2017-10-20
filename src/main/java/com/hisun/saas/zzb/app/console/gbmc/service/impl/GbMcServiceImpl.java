package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbMcServiceImpl extends BaseServiceImpl<GbMc,String> implements GbMcService {

    private GbMcDao gbMcDao;

    @Autowired
    public void setBaseDao(BaseDao<GbMc, String> gbMcDao) {
        this.baseDao = gbMcDao;
        this.gbMcDao = (GbMcDao) gbMcDao;
    }


    @Override
    public int getA01Count(String id) throws Exception {
        int a01Count = 0;
        GbMc gbmc = this.getByPK(id);
        List<GbMcB01> mcBo1s = gbmc.getGbMcB01s();
        if(mcBo1s!=null && mcBo1s.size()>0){
            for(GbMcB01 gbMcB01 : mcBo1s){
                a01Count = a01Count+gbMcB01.getGbMcA01s().size();
            }
        }
        return a01Count;
    }
}
