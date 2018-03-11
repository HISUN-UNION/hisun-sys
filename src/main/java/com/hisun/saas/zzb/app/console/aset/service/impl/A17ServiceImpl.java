package com.hisun.saas.zzb.app.console.aset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.aset.dao.A17Dao;
import com.hisun.saas.zzb.app.console.aset.entity.A17;
import com.hisun.saas.zzb.app.console.aset.service.A17Service;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.util.DateUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class A17ServiceImpl extends BaseServiceImpl<A17,String> implements A17Service {

    private A17Dao a17Dao;

    @Resource
    private AppAsetA01Service appAsetA01Service;


    @Autowired
    public void setBaseDao(BaseDao<A17, String> a17Dao) {
        this.baseDao = a17Dao;
        this.a17Dao = (A17Dao) a17Dao;
    }

    @Override
    public int saveFromYw(DataSource dataSource) throws Exception {
        return 0;
    }

    @Override
    public int saveFromZdwx(DataSource dataSource) throws Exception {
        return 0;
    }
}
