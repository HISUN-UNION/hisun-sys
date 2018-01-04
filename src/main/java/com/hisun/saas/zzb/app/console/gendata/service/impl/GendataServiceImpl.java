package com.hisun.saas.zzb.app.console.gendata.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.apiregister.dao.ApiRegisterDao;
import com.hisun.saas.zzb.app.console.apiregister.entity.ApiRegister;
import com.hisun.saas.zzb.app.console.apiregister.service.ApiRegisterService;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA02;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA02Service;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl2B01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFl2B01Service;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFlService;
import com.hisun.saas.zzb.app.console.gbcx.service.GbcxService;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.*;
import com.hisun.saas.zzb.app.console.gbmc.service.*;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gbtj.service.GbtjService;
import com.hisun.saas.zzb.app.console.gendata.dao.GendataDao;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.gendata.vo.GendataVo;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcDao;
import com.hisun.saas.zzb.app.console.shpc.entity.*;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GendataServiceImpl extends BaseServiceImpl<Gendata, String> implements GendataService {

    @Resource
    private ShpcService shpcService;
    @Resource
    private GbtjService gbtjService;
    @Resource
    private GbMcService gbMcService;
    @Resource
    private ApiRegisterService apiRegisterService;
    @Resource
    private GbcxService gbcxService;



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
    public String saveAppData(Gendata gendata, Map<String, String> selectedMap) throws Exception {
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

        if (selectedMap != null && selectedMap.size() > 0) {
            for (Iterator<String> it = selectedMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = selectedMap.get(key);
                String[] ids = value.split(",");
                if (key.equals(GendataVo.SHPC_DATA)) {
                    for (String id : ids) {
                        this.shpcService.saveAsSqlite(id,sqliteDB,imgdir,attsdir);
                    }
                } else if (key.equals(GendataVo.GBTJ_DATA)) {
                    for (String id : ids) {
                        this.gbtjService.saveAsSqlite(id, sqliteDB,imgdir,attsdir);
                    }
                } else if (key.equals(GendataVo.GBMC_DATA)) {
                    for (String id : ids) {
                        this.gbMcService.saveAsSqlite(id,sqliteDB,imgdir,attsdir);
                    }
                }else if (key.equals(GendataVo.GBCX_DATA)) {
                    //生成干部查询数据包
                    this.gbcxService.saveAsSqlite(sqliteDB,imgdir,attsdir);
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


    public  String saveAppDataFromAnotherAppData(Gendata newPacket,Map<String,String> selectedMap,
                                         Gendata oldPacket,Map<String,String> selectedMapFromOldPacket)throws Exception{


        return "";
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
        List<ApiRegister> apiRegisters = this.apiRegisterService.list();
        if (apiRegisters != null && apiRegisters.size() > 0) {
            for (ApiRegister apiRegister : apiRegisters) {
                sqliteDBUtil.insert(sqlite, apiRegister.toSqliteInsertSql());
            }
        }
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
