package com.hisun.saas.zzb.app.console.gendata.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.apiregister.dao.ApiRegisterDao;
import com.hisun.saas.zzb.app.console.apiregister.entity.ApiRegister;
import com.hisun.saas.zzb.app.console.aset.dao.AppAsetA01Dao;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA02;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetB01Dao;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFl2B01Dao;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFlDao;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl2B01;
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
import com.hisun.util.*;
import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider;
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
public class GendataServiceImpl extends BaseServiceImpl<Gendata, String> implements GendataService {

    @Resource
    private ShpcDao shpcDao;
    @Resource
    private GbtjDao gbtjDao;
    @Resource
    private GbMcDao gbMcDao;
    @Resource
    private ApiRegisterDao apiRegisterDao;
    @Resource
    private AppBsetB01Dao appBsetB01Dao;
    @Resource
    private AppBsetFlDao appBsetFlDao;
    @Resource
    private AppAsetA01Dao appAsetA01Dao;


    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;


    private GendataDao gendataDao;

    @Autowired
    public void setBaseDao(BaseDao<Gendata, String> gendataDao) {
        this.baseDao = gendataDao;
        this.gendataDao = (GendataDao) gendataDao;
    }

    public String saveAppInitData(Gendata gendata) throws Exception {
        //初始化数据目录
        String uuid = UUIDUtil.getUUID();
        String dataDir = uploadAbsolutePath + GendataService.DATA_PATH + uuid + File.separator;
        String appDataZipPath = GendataService.DATA_PATH + UUIDUtil.getUUID() + ".zip";
        String appDataZipRealPath = uploadAbsolutePath + appDataZipPath;

        List<String> dirs = new ArrayList<>();
        String dbdir = dataDir + GendataService.DB_PATH;
        dirs.add(dbdir);
        String imgdir = dataDir + GendataService.IMG_PATH;
        dirs.add(imgdir);
        String attsdir = dataDir + GendataService.ATTS_PATH;
        dirs.add(attsdir);
        //初始化非机构化数据存储目录
        this.initDataDir(dirs);
        String sqliteDB = dbdir + GendataService.SQLITE_DB_NAME;
        //初始化sqlite数据库
        this.initSqlite(sqliteDB);
        //生成配置数据包
        this.genConfigData(dbdir + GendataService.SQLITE_DB_NAME);

        //压缩数据文件
        CompressUtil.zip(appDataZipRealPath, dataDir, GendataService.DATA_PACKET_NAME);
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


    @Override
    public String saveAppData(Gendata gendata, Map<String, String> map) throws Exception {
        //初始化数据目录
        String uuid = UUIDUtil.getUUID();
        String dataDir = uploadAbsolutePath + GendataService.DATA_PATH + uuid + File.separator;
        String appDataZipPath = GendataService.DATA_PATH + UUIDUtil.getUUID() + ".zip";
        String appDataZipRealPath = uploadAbsolutePath + appDataZipPath;

        List<String> dirs = new ArrayList<>();
        String dbdir = dataDir + GendataService.DB_PATH;
        dirs.add(dbdir);
        String imgdir = dataDir + GendataService.IMG_PATH;
        dirs.add(imgdir);
        String attsdir = dataDir + GendataService.ATTS_PATH;
        dirs.add(attsdir);
        //初始化非机构化数据存储目录
        this.initDataDir(dirs);
        String sqliteDB = dbdir + GendataService.SQLITE_DB_NAME;
        //初始化sqlite数据库
        this.initSqlite(sqliteDB);

        if (map != null && map.size() > 0) {
            for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = map.get(key);
                String[] ids = value.split(",");
                if (key.equals(GendataVo.SHPC_DATA)) {
                    for (String id : ids) {
                        this.genShpcData(id, sqliteDB, imgdir, attsdir);
                    }
                } else if (key.equals(GendataVo.GBTJ_DATA)) {
                    for (String id : ids) {
                        this.genGbtjData(id, sqliteDB);
                    }
                } else if (key.equals(GendataVo.GBMC_DATA)) {
                    for (String id : ids) {
                        this.genGbmcData(id, sqliteDB, imgdir, attsdir);
                    }
                }else if (key.equals(GendataVo.GBCX_DATA)) {
                    //生成干部查询数据包
                    this.genGbcxData(sqliteDB,imgdir,attsdir);
                }

            }
            //生成配置数据包
            this.genConfigData(dbdir + GendataService.SQLITE_DB_NAME);
        }
        //压缩数据文件
        CompressUtil.zip(appDataZipRealPath, dataDir, GendataService.DATA_PACKET_NAME);
        gendata.setPath(appDataZipPath);

        File f = new File(appDataZipRealPath);
        //FileInputStream inputStream = new FileInputStream(f);
        //gendata.setPacketMd5(DigestUtils.md5Hex(IOUtils.toByteArray(inputStream)));
        gendata.setPacketMd5(Md5Util.getMD5(f));
        gendata.setPacketSize(Long.toString(f.length()));

        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        BeanTrans.setBaseProperties(gendata, userLoginDetails, "save");
        this.save(gendata);
        return gendata.getId();
    }


    private void initDataDir(List<String> dirs) {
        if (dirs != null) {
            for (String s : dirs) {
                //初始化数据存储目录
                File dir = new File(s);
                if (dir.exists() == false) {
                    dir.mkdirs();
                }
            }
        }

    }

    private void genConfigData(String sqlite) throws Exception {
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        //api数据
        List<ApiRegister> apiRegisters = this.apiRegisterDao.list();
        if (apiRegisters != null && apiRegisters.size() > 0) {
            for (ApiRegister apiRegister : apiRegisters) {
                sqliteDBUtil.insert(sqlite, apiRegister.toSqliteInsertSql());
            }
        }
    }


    private void genGbtjData(String gbtjId, String sqlite) throws Exception {
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        Gbtj gbtj = this.gbtjDao.getByPK(gbtjId);
        sqliteDBUtil.insert(sqlite, gbtj.toSqliteInsertSql());
    }

    private void genShpcData(String shpcId, String sqlite, String imgDir, String attsDir) throws Exception {
        Shpc shpc = this.shpcDao.getPK(shpcId);
        if (shpc != null) {
            SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
            sqliteDBUtil.insert(sqlite, shpc.toSqliteInsertSql());
            if (shpc.getFilePath() != null && shpc.getFilePath().equals("") == false) {
                this.copyFile(shpc.getFilePath(), attsDir);
            }
            //上会批次附件
            List<ShpcAtts> shpcAttses = shpc.getShpcAttses();
            if (shpcAttses != null) {
                for (ShpcAtts shpcAtts : shpcAttses) {
                    //初始化结构数据
                    sqliteDBUtil.insert(sqlite, shpcAtts.toSqliteInsertSql());
                    if (StringUtils.isEmpty(shpcAtts.getFilepath()) == false) {
                        this.copyFile(shpcAtts.getFilepath(), attsDir);
                    }
                }
            }
            //干部
            List<Sha01> sha01s = shpc.getSha01s();
            if (sha01s != null) {
                for (Sha01 sha01 : sha01s) {
                    //初始化结构数据
                    sqliteDBUtil.insert(sqlite, sha01.toSqliteInsertSql());
                    //初始化非机构化数据
                    if (sha01.getZppath() != null) {
                        this.copyFile(sha01.getZppath(), imgDir);
                    }
                    //工作经历
                    List<Sha01gzjl> gzjls = sha01.getGzjls();
                    if (gzjls != null) {
                        for (Sha01gzjl sha01gzjl : gzjls) {
                            sqliteDBUtil.insert(sqlite, sha01gzjl.toSqliteInsertSql());
                        }
                    }
                    //干部任免审批表
                    List<Sha01gbrmspb> sha01gbrmspbs = sha01.getGbrmspbs();
                    if (sha01gbrmspbs != null) {
                        for (Sha01gbrmspb gbrmspb : sha01gbrmspbs) {
                            sqliteDBUtil.insert(sqlite, gbrmspb.toSqliteInsertSql());
                            if (gbrmspb.getFile2imgPath() != null) {
                                this.copyFile(gbrmspb.getFile2imgPath(), attsDir);
                            }
                        }
                    }
                    //考察材料
                    List<Sha01kccl> sha01kccls = sha01.getKccls();
                    if (sha01kccls != null) {
                        for (Sha01kccl kccl : sha01kccls) {
                            sqliteDBUtil.insert(sqlite, kccl.toSqliteInsertSql());
                            if (kccl.getFile2imgPath() != null) {
                                this.copyFile(kccl.getFile2imgPath(), attsDir);
                            }
                        }
                    }
                    //档案任前审核表
                    List<Sha01dascqk> sha01dascqks = sha01.getDascqks();
                    if (sha01dascqks != null) {
                        for (Sha01dascqk sha01dascqk : sha01dascqks) {
                            sqliteDBUtil.insert(sqlite, sha01dascqk.toSqliteInsertSql());
                            if (sha01dascqk.getFile2imgPath() != null) {
                                this.copyFile(sha01dascqk.getFile2imgPath(), attsDir);
                            }
                            //档案审查表提示表
                            List<Sha01dascqktips> sha01dascqktipses = sha01dascqk.getSha01dascqktips();
                            if (sha01dascqktipses != null) {
                                for (Sha01dascqktips tip : sha01dascqktipses) {
                                    sqliteDBUtil.insert(sqlite, tip.toSqliteInsertSql());
                                }
                            }
                        }
                    }
                    //个人重大事项
                    List<Sha01grzdsx> sha01grzdsxes = sha01.getGrzdsxes();
                    if (sha01grzdsxes != null) {
                        for (Sha01grzdsx sha01grzdsx : sha01grzdsxes) {
                            sqliteDBUtil.insert(sqlite, sha01grzdsx.toSqliteInsertSql());
                            if (sha01grzdsx.getFile2imgPath() != null) {
                                this.copyFile(sha01grzdsx.getFile2imgPath(), attsDir);
                            }
                        }
                    }
                }
            }
        }
    }


    private void genGbmcData(String mcId, String sqlite, String imgDir, String attsDir) throws Exception {
        GbMc gbMc = this.gbMcDao.getPK(mcId);
        if (gbMc != null) {
            SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
            sqliteDBUtil.insert(sqlite, gbMc.toSqliteInsertSql());
            //单位目录
            List<GbMcB01> gbMcB01s = gbMc.getGbMcB01s();
            if (gbMcB01s != null) {
                for (GbMcB01 gbMcB01 : gbMcB01s) {
                    sqliteDBUtil.insert(sqlite, gbMcB01.toSqliteInsertSql());
                    List<GbMcA01> gbMcA01s = gbMcB01.getGbMcA01s();
                    for (GbMcA01 gbMcA01 : gbMcA01s) {
                        //初始化结构数据
                        sqliteDBUtil.insert(sqlite, gbMcA01.toSqliteInsertSql());
                        //初始化非机构化数据
                        if (gbMcA01.getZppath() != null) {
                            this.copyFile(gbMcA01.getZppath(), imgDir);
                        }
                        //工作经历
                        List<GbMcA01gzjl> gbMcA01gzjls = gbMcA01.getGbMca01gzjls();
                        if (gbMcA01gzjls != null) {
                            for (GbMcA01gzjl gbMcA01gzjl : gbMcA01gzjls) {
                                sqliteDBUtil.insert(sqlite, gbMcA01gzjl.toSqliteInsertSql());
                            }
                        }
                        //干部任免审批表
                        List<GbMcA01gbrmspb> gbMcA01gbrmspbs = gbMcA01.getGbMca01gbrmspbs();
                        if (gbMcA01gbrmspbs != null) {
                            for (GbMcA01gbrmspb gbrmspb : gbMcA01gbrmspbs) {
                                sqliteDBUtil.insert(sqlite, gbrmspb.toSqliteInsertSql());
                                if (gbrmspb.getFile2imgPath() != null) {
                                    this.copyFile(gbrmspb.getFile2imgPath(), attsDir);
                                }
                            }
                        }

                    }
                }
            }
        }
    }


    private void genGbcxData(String sqlite, String imgDir, String attsDir) throws Exception {
        imgDir +="aset/";
        attsDir +="aset/";
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        //分类及机构
        List<AppBsetFl> appBsetFls = this.appBsetFlDao.list();
        for (AppBsetFl appBsetFl : appBsetFls) {
            sqliteDBUtil.insert(sqlite, appBsetFl.toSqliteInsertSql());
        }
        List<AppBsetB01> appBsetB01s = this.appBsetB01Dao.list();
        for (AppBsetB01 b01 : appBsetB01s) {
            sqliteDBUtil.insert(sqlite, b01.toSqliteInsertSql());
            List<AppBsetFl2B01> appBsetFl2B01s = b01.getAppBsetFl2B01s();
            for (AppBsetFl2B01 appBsetFl2B01 : appBsetFl2B01s) {
                sqliteDBUtil.insert(sqlite, appBsetFl2B01.toSqliteInsertSql());
            }
        }
        //干部信息
        List<AppAsetA01> appAsetA01s = this.appAsetA01Dao.list();
        for(AppAsetA01 appAsetA01: appAsetA01s){
            sqliteDBUtil.insert(sqlite, appAsetA01.toSqliteInsertSql());
//            if(appAsetA01.getZpPath()!=null
//                    && appAsetA01.getZpPath().isEmpty()==false){
//                this.copyFile(appAsetA01.getZpPath(),imgDir);
//            }
            if(appAsetA01.getFile2ImgPath()!=null
                    &&appAsetA01.getFile2ImgPath().isEmpty()==false){
                this.copyFile(appAsetA01.getFile2ImgPath(),attsDir);
            }
            List<AppAsetA02> appAsetA02s = appAsetA01.getAppAsetA02s();
            for(AppAsetA02 appAsetA02 : appAsetA02s){
                sqliteDBUtil.insert(sqlite, appAsetA02.toSqliteInsertSql());
            }
        }
    }


    public void copyFile(String source, String targetPath) throws IOException {
        File sourceFile = new File(uploadAbsolutePath + source);
        File targetFile = new File(targetPath + sourceFile.getName());
        FileUtils.copyFile(sourceFile, targetFile);
    }

    private void initSqlite(String sqlite) throws Exception {

        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        sqliteDBUtil.createDatabase(sqlite);

        InputStream inputStream = GendataServiceImpl.class.getClassLoader().getResourceAsStream("zzb-app.sql");
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(isr);
        int len = 0;
        StringBuffer sb = new StringBuffer("");
        String line = null;
        while ((line = in.readLine()) != null) {

            if (len != 0) {
                sb.append(line);
            } else {
                sb.append(line);
            }
            len++;
        }
        in.close();
        isr.close();
        sqliteDBUtil.createTables(sqlite, sb.toString());
    }

}
