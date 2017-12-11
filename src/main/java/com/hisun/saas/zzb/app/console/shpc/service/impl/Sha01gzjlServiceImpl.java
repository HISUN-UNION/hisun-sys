package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01gzjlDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gzjl;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gzjlService;
import com.hisun.saas.zzb.app.console.util.GzjlUtil;
import com.hisun.util.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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



    public void saveFromWord(Sha01 sha01, Map<String, String> dataMap){

        String gzjlStr = null;
        Map<String,String> listDataMap = new HashMap<String,String>();
        for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            //组合主表以"list"
            if(key.startsWith(WordUtil.listSign) && key.indexOf("APP_SH_A01_GZJL")>0) {
                gzjlStr= dataMap.get(key);
            }
        }
        this.saveGzjls(sha01,gzjlStr);
    }

    public void saveGzjls(Sha01 sha01,String gzjlStr){
        Sha01gzjl gzjl = null;
        //解析gzjlStr
        if(gzjlStr!=null){
            int px =1;
            List<String> list = GzjlUtil.matchGzjlStr(gzjlStr);
            for(String str : list){
                gzjl = new Sha01gzjl();
                gzjl.setSha01(sha01);
                gzjl.setTenant(sha01.getTenant());
                gzjl.setJlsm(str);
                gzjl.setPx(px);
                px++;
                this.sha01gzjlDao.save(gzjl);
            }
        }
    }

}