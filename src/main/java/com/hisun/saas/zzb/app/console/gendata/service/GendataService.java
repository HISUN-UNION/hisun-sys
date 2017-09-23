package com.hisun.saas.zzb.app.console.gendata.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;

import java.io.File;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GendataService extends BaseService<Gendata,String>{

   public static String DATA_PATH = File.separator+"appdata"+File.separator;
   public static String IMG_PATH="img"+File.separator;
   public static String ATTS_PATH="atts"+File.separator;
   public static String DB_PATH="db"+File.separator;
   public static String SQLITE_DB_NAME="zzb-app.db";
   public static String DATA_PACKET_NAME="zzb-app-android";


   public String saveAppData(Gendata gendata,Map<String,String> map,String appDataDir) throws Exception;


}
