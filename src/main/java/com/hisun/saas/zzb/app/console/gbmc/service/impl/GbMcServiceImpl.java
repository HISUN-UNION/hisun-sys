package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01Dao;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcB01Dao;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.*;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.util.GzjlUtil;
import com.hisun.util.JacksonUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbMcServiceImpl extends BaseServiceImpl<GbMc,String> implements GbMcService {

    private GbMcDao gbMcDao;

    @Resource
    private GbMcB01Dao gbMcB01Dao;
    @Resource
    private GbMcA01Dao gbMcA01Dao;

    @Autowired
    public void setBaseDao(BaseDao<GbMc, String> gbMcDao) {
        this.baseDao = gbMcDao;
        this.gbMcDao = (GbMcDao) gbMcDao;
    }


    @Override
    public int getA01Count(String id) throws Exception {
        int a01Count = 0;
        GbMc gbmc = this.getByPK(id);
//        List<GbMcB01> mcBo1s = gbmc.getGbMcB01s();
//        if(mcBo1s!=null && mcBo1s.size()>0){
//            for(GbMcB01 gbMcB01 : mcBo1s){
//                a01Count = a01Count+gbMcB01.getGbMcA01s().size();
//            }
//        }
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
        query.add(CommonRestrictions.and(" gbMc.id = :mcid", "mcid", id));
        return ((Long)gbMcA01Dao.count(query)).intValue();
    }


    public void saveFromWordDataMap(GbMc gbMc,int isMl, List<Map<String, String>> dataList){
        this.gbMcDao.save(gbMc);
        //判断是否有目录
        int px =1;
        if(isMl==GbMc.YML){
            for(Map<String,String> dataMap : dataList){
                String b0101=this.getB0101(dataMap);
                GbMcB01 b01= new GbMcB01();
                b01.setB0101(b0101);
                b01.setGbMc(gbMc);
                b01.setIsDisplay(GbMcB01.DISPLAY);
                b01.setPx(px);
                b01.setTenant(gbMc.getTenant());
                this.gbMcB01Dao.save(b01);
                this.gbMcB01Dao.flush();//执行sql,不然原生SQL认不到b01.id
                this.gbMcA01Dao.saveFromWordDataMap(dataMap,b01);
                px++;
            }
        }else{
            GbMcB01 b01= new GbMcB01();
            b01.setB0101("隐藏目录");
            b01.setGbMc(gbMc);
            b01.setIsDisplay(GbMcB01.HIDDEN);
            b01.setPx(px);
            b01.setTenant(gbMc.getTenant());
            this.gbMcB01Dao.save(b01);
            this.gbMcB01Dao.flush();
            for(Map<String,String> dataMap : dataList){
                this.gbMcA01Dao.saveFromWordDataMap(dataMap,b01);
                px++;
            }

        }
    }

    private String getB0101(Map<String,String> dataMap){
        String s ="";
        String key = "";
        for(Iterator<String> it = dataMap.keySet().iterator(); it.hasNext();){
            key = it.next();
            String value = dataMap.get(key);
            if(key.toLowerCase().indexOf("b0101")>-1){
                s= value;
                break;
            }
        }
        dataMap.remove(key);
        return s;
    }




}
