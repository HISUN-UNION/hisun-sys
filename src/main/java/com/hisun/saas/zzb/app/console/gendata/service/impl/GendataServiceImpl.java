package com.hisun.saas.zzb.app.console.gendata.service.impl;

import com.hisun.base.exception.GenericException;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.gendata.vo.GendataVo;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcDao;
import com.hisun.saas.zzb.app.console.shpc.entity.*;
import com.hisun.util.SqliteDBUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

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
    public String genAppData(Map<String, String> map, String dataPath) throws Exception {
        String appDataZipPath = null;
        //初始化数据目录
        String uuid = UUIDUtil.getUUID();
        List<String> dirs = new ArrayList<>();
        String dbdir = dataPath + uuid + File.separator + GendataService.DB_PATH;
        dirs.add(dbdir);
        String imgdir = dataPath + uuid + File.separator +GendataService.IMG_PATH ;
        dirs.add(imgdir);
        String attsdir = dataPath + uuid + File.separator + GendataService.ATTS_PATH;
        dirs.add(attsdir);
        //初始化非机构化数据存储目录
        this.initDataDir(dirs);

        map = new HashMap<String,String>();
        map.put(GendataVo.SHPC_DATA,"402881ea5e98e964015e990b91850020");
        if (map != null && map.size() > 0) {
            //初始化sqlite数据库
            this.initSqlite(dbdir + GendataService.SQLITE_DB_NAME);
            //生成数据包
            for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = map.get(key);
                if (key.equals(GendataVo.SHPC_DATA)) {
                    String[] ids = value.split(",");
                    for(String id :ids){
                        this.genShpcData(id,dbdir + GendataService.SQLITE_DB_NAME,imgdir,attsdir);
                    }
                }else if (key.equals(GendataVo.GBTJ_DATA)){

                }
            }
            //初始化非机构化数据

        }
        return appDataZipPath;
    }


    private void initDataDir(List<String> dirs){
        if(dirs!=null){
            for(String s : dirs){
                //初始化数据存储目录
                File dir = new File(s);
                if(dir.exists()==false){
                    dir.mkdirs();
                }
            }
        }

    }


    private void genShpcData(String shpcId,String sqlite,String imgDir,String attsDir) throws Exception{
        Shpc shpc = shpcDao.getPK(shpcId);
        if(shpc!=null){
            SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
            sqliteDBUtil.insert(sqlite,shpc.toInsertSql());
            //干部
            List<Sha01> sha01s = shpc.getSha01s();
            if(sha01s!=null) {
                for (Sha01 sha01 : sha01s) {
                    //初始化结构数据
                    sqliteDBUtil.insert(sqlite, sha01.toInsertSql());
                    //初始化非机构化数据
                    if(sha01.getZppath()!=null) {
                        this.copyFile(sha01.getZppath(), imgDir);
                    }
                    //干部任免审批表
                    List<Sha01gbrmspb> sha01gbrmspbs = sha01.getGbrmspbs();
                    if(sha01gbrmspbs!=null){
                        for(Sha01gbrmspb gbrmspb : sha01gbrmspbs){
                            sqliteDBUtil.insert(sqlite, gbrmspb.toInsertSql());
                        }
                    }
                    //考察材料
                    List<Sha01kccl> sha01kccls = sha01.getKccls();
                    if(sha01kccls!=null){
                        for (Sha01kccl kccl : sha01kccls){
                              sqliteDBUtil.insert(sqlite, kccl.toInsertSql());
                        }
                    }
                    //档案任前审核表
                    List<Sha01dascqk> sha01dascqks = sha01.getDascqks();
                    if(sha01dascqks!=null){
                        for(Sha01dascqk sha01dascqk : sha01dascqks){
                            sqliteDBUtil.insert(sqlite,sha01dascqk.toInsertSql());
                            //档案审查表提示表
                            List<Sha01dascqktips> sha01dascqktipses = sha01dascqk.getSha01dascqktips();
                            if(sha01dascqktipses!=null){
                                for(Sha01dascqktips tip : sha01dascqktipses){
                                    sqliteDBUtil.insert(sqlite,tip.toInsertSql());
                                }
                            }
                        }
                    }
                    //个人重大事项
                    List<Sha01grzdsx> sha01grzdsxes = sha01.getGrzdsxes();
                    if(sha01grzdsxes!=null){
                        for(Sha01grzdsx sha01grzdsx : sha01grzdsxes){
                            sqliteDBUtil.insert(sqlite,sha01grzdsx.toInsertSql());
                        }
                    }
                }
            }
        }
    }

    public void copyFile(String source,String targetPath) throws IOException{
        File sourceFile = new File(source);
        File targetFile = new File(targetPath+sourceFile.getName());
        FileUtils.copyFile(sourceFile,targetFile);
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
