package com.hisun.saas.zzb.app.console.gendata.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.apiregister.dao.ApiRegisterDao;
import com.hisun.saas.zzb.app.console.apiregister.entity.ApiRegister;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.*;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gendata.dao.GendataDao;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.gendata.vo.GendataVo;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcDao;
import com.hisun.saas.zzb.app.console.shpc.entity.*;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.CompressUtil;
import com.hisun.util.SqliteDBUtil;
import com.hisun.util.StringUtils;
import com.hisun.util.UUIDUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GendataServiceImpl extends BaseServiceImpl<Gendata,String> implements GendataService {

    @Resource
    private ShpcDao shpcDao;
    @Resource
    private GbtjDao gbtjDao;
    @Resource
    private GbMcDao gbMcDao;
    @Resource
    private ApiRegisterDao apiRegisterDao;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;


    private GendataDao gendataDao;

    @Autowired
    public void setBaseDao(BaseDao<Gendata, String> gendataDao) {
        this.baseDao = gendataDao;
        this.gendataDao = (GendataDao) gendataDao;
    }



    @Override
    public String saveAppData(Gendata gendata,Map<String, String> map) throws Exception {
        //初始化数据目录
        String uuid = UUIDUtil.getUUID();
        String dataDir = uploadAbsolutePath+ GendataService.DATA_PATH + uuid + File.separator;
        String appDataZipPath = GendataService.DATA_PATH+UUIDUtil.getUUID()+".zip";
        String appDataZipRealPath = uploadAbsolutePath +appDataZipPath;

        List<String> dirs = new ArrayList<>();
        String dbdir =  dataDir+ GendataService.DB_PATH;
        dirs.add(dbdir);
        String imgdir = dataDir+GendataService.IMG_PATH ;
        dirs.add(imgdir);
        String attsdir = dataDir+ GendataService.ATTS_PATH;
        dirs.add(attsdir);
        //初始化非机构化数据存储目录
        this.initDataDir(dirs);
        String sqliteDB = dbdir + GendataService.SQLITE_DB_NAME;

        if (map != null && map.size() > 0) {
            //初始化sqlite数据库
            this.initSqlite(sqliteDB);
            //生成会议研究数据包
            for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = map.get(key);
                String[] ids = value.split(",");
                if (key.equals(GendataVo.SHPC_DATA)) {
                    for(String id :ids){
                        this.genShpcData(id,sqliteDB,imgdir,attsdir);
                    }
                }else if (key.equals(GendataVo.GBTJ_DATA)){
                    for(String id :ids){
                        this.genGbtjData(id,sqliteDB);
                    }
                }else if(key.equals(GendataVo.GBMC_DATA)){
                    for(String id :ids){
                        this.genGbmcData(id,sqliteDB,imgdir,attsdir);
                    }
                }
            }
            //生成配置数据包
            this.genConfigData(dbdir + GendataService.SQLITE_DB_NAME);
        }
        //压缩数据文件
        CompressUtil.zip(appDataZipRealPath,dataDir,GendataService.DATA_PACKET_NAME);
        gendata.setPath(appDataZipPath);

        File f = new File(appDataZipRealPath);
        FileInputStream inputStream = new FileInputStream(f);
        gendata.setPacketMd5(DigestUtils.md5Hex(IOUtils.toByteArray(inputStream)));
        gendata.setPacketSize(Long.toString(f.length()));

        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        BeanTrans.setBaseProperties(gendata, userLoginDetails, "save");
        this.save(gendata);
        return gendata.getId();
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

    private void genConfigData(String sqlite)throws Exception{
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        //api数据
        List<ApiRegister> apiRegisters = this.apiRegisterDao.list();
        if(apiRegisters!=null && apiRegisters.size()>0){
            for(ApiRegister apiRegister : apiRegisters){
                sqliteDBUtil.insert(sqlite,apiRegister.toSqliteInsertSql());
            }
        }
    }


    private void genGbtjData(String gbtjId,String sqlite)throws Exception{
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        Gbtj gbtj = this.gbtjDao.getByPK(gbtjId);
        sqliteDBUtil.insert(sqlite,gbtj.toSqliteInsertSql());
    }

    private void genShpcData(String shpcId,String sqlite,String imgDir,String attsDir) throws Exception{
        Shpc shpc = this.shpcDao.getPK(shpcId);
        if(shpc!=null){
            SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
            sqliteDBUtil.insert(sqlite,shpc.toSqliteInsertSql());
            if(shpc.getFilePath()!=null&& shpc.getFilePath().equals("")==false){
                this.copyFile(shpc.getFilePath(),attsDir);
            }
            //上会批次附件
            List<ShpcAtts> shpcAttses = shpc.getShpcAttses();
            if(shpcAttses!=null){
                for(ShpcAtts shpcAtts : shpcAttses){
                    //初始化结构数据
                    sqliteDBUtil.insert(sqlite, shpcAtts.toSqliteInsertSql());
                    if(StringUtils.isEmpty(shpcAtts.getFilepath())==false){
                        this.copyFile(shpcAtts.getFilepath(),attsDir);
                    }
                }
            }
            //干部
            List<Sha01> sha01s = shpc.getSha01s();
            if(sha01s!=null) {
                for (Sha01 sha01 : sha01s) {
                    //初始化结构数据
                    sqliteDBUtil.insert(sqlite, sha01.toSqliteInsertSql());
                    //初始化非机构化数据
                    if(sha01.getZppath()!=null) {
                        this.copyFile(sha01.getZppath(), imgDir);
                    }
                    //工作经历
                    List<Sha01gzjl> gzjls = sha01.getGzjls();
                    if(gzjls!=null){
                        for(Sha01gzjl sha01gzjl : gzjls){
                            sqliteDBUtil.insert(sqlite, sha01gzjl.toSqliteInsertSql());
                        }
                    }
                    //干部任免审批表
                    List<Sha01gbrmspb> sha01gbrmspbs = sha01.getGbrmspbs();
                    if(sha01gbrmspbs!=null){
                        for(Sha01gbrmspb gbrmspb : sha01gbrmspbs){
                            sqliteDBUtil.insert(sqlite, gbrmspb.toSqliteInsertSql());
                            if(gbrmspb.getFile2imgPath()!=null){
                                this.copyFile(gbrmspb.getFile2imgPath(),attsDir);
                            }
                        }
                    }
                    //考察材料
                    List<Sha01kccl> sha01kccls = sha01.getKccls();
                    if(sha01kccls!=null){
                        for (Sha01kccl kccl : sha01kccls){
                            sqliteDBUtil.insert(sqlite, kccl.toSqliteInsertSql());
                            if(kccl.getFile2imgPath()!=null){
                                this.copyFile(kccl.getFile2imgPath(),attsDir);
                            }
                        }
                    }
                    //档案任前审核表
                    List<Sha01dascqk> sha01dascqks = sha01.getDascqks();
                    if(sha01dascqks!=null){
                        for(Sha01dascqk sha01dascqk : sha01dascqks){
                            sqliteDBUtil.insert(sqlite,sha01dascqk.toSqliteInsertSql());
                            if(sha01dascqk.getFile2imgPath()!=null){
                                this.copyFile(sha01dascqk.getFile2imgPath(),attsDir);
                            }
                            //档案审查表提示表
                            List<Sha01dascqktips> sha01dascqktipses = sha01dascqk.getSha01dascqktips();
                            if(sha01dascqktipses!=null){
                                for(Sha01dascqktips tip : sha01dascqktipses){
                                    sqliteDBUtil.insert(sqlite,tip.toSqliteInsertSql());
                                }
                            }
                        }
                    }
                    //个人重大事项
                    List<Sha01grzdsx> sha01grzdsxes = sha01.getGrzdsxes();
                    if(sha01grzdsxes!=null){
                        for(Sha01grzdsx sha01grzdsx : sha01grzdsxes){
                            sqliteDBUtil.insert(sqlite,sha01grzdsx.toSqliteInsertSql());
                            if(sha01grzdsx.getFile2imgPath()!=null){
                                this.copyFile(sha01grzdsx.getFile2imgPath(),attsDir);
                            }
                        }
                    }
                }
            }
        }
    }



    private void genGbmcData(String mcId,String sqlite,String imgDir,String attsDir) throws Exception{
        GbMc gbMc = this.gbMcDao.getPK(mcId);
        if(gbMc!=null){
            SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
            sqliteDBUtil.insert(sqlite,gbMc.toSqliteInsertSql());
            //单位目录
            List<GbMcB01> gbMcB01s = gbMc.getGbMcB01s();
            if(gbMcB01s!=null) {
                for (GbMcB01 gbMcB01 : gbMcB01s) {
                    sqliteDBUtil.insert(sqlite, gbMcB01.toSqliteInsertSql());
                    List<GbMcA01> gbMcA01s = gbMcB01.getGbMcA01s();
                    for(GbMcA01 gbMcA01 : gbMcA01s){
                        //初始化结构数据
                        sqliteDBUtil.insert(sqlite, gbMcA01.toSqliteInsertSql());
                        //初始化非机构化数据
                        if(gbMcA01.getZppath()!=null) {
                            this.copyFile(gbMcA01.getZppath(), imgDir);
                        }
                        //工作经历
                        List<GbMcA01gzjl> gbMcA01gzjls = gbMcA01.getGbMca01gzjls();
                        if(gbMcA01gzjls!=null){
                            for(GbMcA01gzjl gbMcA01gzjl : gbMcA01gzjls){
                                sqliteDBUtil.insert(sqlite, gbMcA01gzjl.toSqliteInsertSql());
                            }
                        }
                        //干部任免审批表
                        List<GbMcA01gbrmspb> gbMcA01gbrmspbs = gbMcA01.getGbMca01gbrmspbs();
                        if(gbMcA01gbrmspbs!=null){
                            for(GbMcA01gbrmspb gbrmspb : gbMcA01gbrmspbs){
                                sqliteDBUtil.insert(sqlite, gbrmspb.toSqliteInsertSql());
                                if(gbrmspb.getFile2imgPath()!=null){
                                    this.copyFile(gbrmspb.getFile2imgPath(),attsDir);
                                }
                            }
                        }

                    }
                }
            }
        }
    }


    public void copyFile(String source,String targetPath) throws IOException{
        File sourceFile = new File(uploadAbsolutePath+source);
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

}
