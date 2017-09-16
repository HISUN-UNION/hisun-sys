package com.hisun.saas.zzb.app.console.gendata.service.impl;

import com.hisun.base.exception.GenericException;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.gendata.vo.GendataVo;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.util.SqliteDBUtil;
import com.hisun.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GendataServiceImpl implements GendataService {

    @Resource
    private ShpcDao shpcDao;
    @Resource
    private GbtjDao gbtjDao;


    @Override
    public String genAppData(Map<String, String> map, String appDataDir) throws Exception {
        String appDataZipPath = null;

        String uuid = UUIDUtil.getUUID();
        String dbdir = appDataDir + uuid + File.separator + "db" + File.separator;
        String imgdir = appDataDir + uuid + File.separator + "img" + File.separator;
        String attsdir = appDataDir + uuid + File.separator + "atts" + File.separator;

        //根据选择需要导出的数据,生成数据包
        //1.读取需要导出的数据对象
        //2.初始化sqlite,根据对象生成Insert语句,执行sql
        //3.拷贝附件至数据包目录
        //4.压缩数据包目录zip
        //5.返回数据包目录

        try {
            if (map != null && map.size() > 0) {
                //初始化数据存储目录
                File db = new File(dbdir);
                db.mkdirs();
                File img = new File(imgdir);
                img.mkdirs();
                File atts = new File(attsdir);
                atts.mkdirs();
                //初始化sqlite数据库
                this.initSqlite(dbdir + "zzb-app.db");

                for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
                    String key = it.next();
                    String value = map.get(key);
                    if (key.equals(GendataVo.BWH_DATA)) {

                        String[] ids = value.split(",");
                        for(String id :ids){

                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return appDataZipPath;
    }


    private void insertShpcData(String shpcId,String sqlite) throws Exception{
        Shpc shpc = shpcDao.getPK(shpcId);
        if(shpc!=null){
            SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
            sqliteDBUtil.insert(sqlite,shpc.toInsertSql());
            //干部
            List<Sha01> sha01s = shpc.getSha01s();
            if(sha01s!=null) {
                for (Sha01 sha01 : sha01s) {
                    sqliteDBUtil.insert(sqlite, sha01.toInsertSql());
                    List<Sha01gbrmspb> sha01gbrmspbs = sha01.getGbrmspbs();
                    if(sha01gbrmspbs!=null){

                    }
                }
            }
        }
    }


    private void initSqlite(String sqlite) throws Exception{

        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        sqliteDBUtil.createDatabase(sqlite);

        InputStream inputStream = GendataServiceImpl.class.getClassLoader().getResourceAsStream("zzb-app.sql");
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(isr);
        int len =0;
        StringBuffer sb = new StringBuffer("");
        String line = null;
        while ((line = in.readLine()) != null) {

            if (len != 0) {
                sb.append( line);
            } else {
                sb.append(line);
            }
            len++;
        }
        in.close();
        isr.close();
        sqliteDBUtil.createTables(sqlite,sb.toString());
    }

    public static void main(String[] args) throws Exception{

        GendataServiceImpl impl = new GendataServiceImpl();
        Map<String,String> map = new HashMap<String,String>();
        map.put("a","a");
        impl.genAppData(map,"/Users/zhouying/Documents/workspace/store/appdata/");

    }
}
