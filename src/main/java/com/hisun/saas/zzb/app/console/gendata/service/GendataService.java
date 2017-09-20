package com.hisun.saas.zzb.app.console.gendata.service;

import java.io.File;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GendataService {

   public static String DATA_PATH = File.separator+"appdata"+File.separator;
   public static String IMG_PATH="img"+File.separator;
   public static String ATTS_PATH="atts"+File.separator;
   public static String DB_PATH="db"+File.separator;
   public static String SQLITE_DB_NAME="zzb-app.db";
   public static String DATA_PACKET_NAME="zzb-app-android";


   public String genAppData(Map<String,String> map,String appDataDir) throws Exception;


}
