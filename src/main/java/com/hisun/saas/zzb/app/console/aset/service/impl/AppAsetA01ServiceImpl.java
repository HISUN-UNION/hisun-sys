package com.hisun.saas.zzb.app.console.aset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.aset.dao.AppAsetA01Dao;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.util.DateUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppAsetA01ServiceImpl extends BaseServiceImpl<AppAsetA01,String> implements AppAsetA01Service {

    private AppAsetA01Dao appAsetA01Dao;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<AppAsetA01, String> appAsetA01Dao) {
        this.baseDao = appAsetA01Dao;
        this.appAsetA01Dao = (AppAsetA01Dao) appAsetA01Dao;
    }
    public Integer getMaxPx(String b01Id){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> arg=new HashMap<String, Object>();
        String hql = "select max(t.a01_px) as px from app_gbcx_a01 t where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) and t.b01_id=(:b01_id) order by  t.a01_px asc";
        arg.put("tombstone", "0");
        arg.put("tenant_id", userLoginDetails.getTenantId());
        arg.put("b01_id", b01Id);
        List<Map> maxSorts = this.appAsetA01Dao.countReturnMapBySql(hql, arg);
        Integer maxPx = (Integer) maxSorts.get(0).get("px");
        return maxPx;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(String b01Id,int oldPx,int newPx){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        String sql = "UPDATE app_gbcx_a01 t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.a01_px=t.a01_px-1";
        } else {
            sql = sql + "t.a01_px=t.a01_px+1";
        }

        sql = sql + " where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) and t.b01_id=(:b01_id)";
        if(newPx > oldPx) {
            sql = sql + " and t.a01_px<=" + newPx + " and t.a01_px >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.a01_px<" + oldPx + " and t.a01_px>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("tombstone", "0");
        paramMap.put("tenant_id", userLoginDetails.getTenantId());
        paramMap.put("b01_id", b01Id);
        this.appAsetA01Dao.update(sql, paramMap);
    }


    public int saveAsetA01FromYw(DataSource dataSource)throws Exception{

        //初始化照片存储目录
        String photoStoreDir = uploadAbsolutePath + AppAsetA01Service.ZP_PATH;
        File photoStoreDirFile = new File(photoStoreDir);
        if(photoStoreDirFile.exists()==false){
            photoStoreDirFile.mkdirs();
        }

        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from a000 where a000.a000_a0194 in ('01','31','11')  ", new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = countList.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                Object value = e.getValue();
                count = ((Integer)value).intValue();
            }
        }
        //每次处理400条
        int dealCount = count/400;
        for(int i=0;i<=dealCount;i++){
            int num = i*400;
            String sql = " SELECT top 400 * FROM A000 where  A000.a000_a0194 in ('01','31','11') and A000.PERSONCODE not in "
                    + "(select top "+num+" A000.PERSONCODE from A000 where  A000.a000_a0194 in ('01','31','11')  order by A000.PERSONCODE)";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();
                fields.append("insert into app_aset_a01 (");
                fields.append(" tombstone ");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");
                String personCode = "";
                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if(key.equalsIgnoreCase("PERSONCODE")){
                        fields.append(",id");
                        values.append(",'"+value+"'");
                        personCode = value.toString();
                    }else if(key.equalsIgnoreCase("A000_A0101")){
                        fields.append(",xm");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0104_SHOW")){
                        fields.append(",xb");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0117_SHOW")){
                        fields.append(",mz");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0129A")){
                        fields.append(",zw");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0114_SHOW")){
                        fields.append(",csd");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0111_SHOW")){
                        fields.append(",jg");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A010")){
                        fields.append(",csny");
                        if(value.toString().equals("")==false){
                            values.append(",'"+ DateUtil.formatDateByFormat((Date) value,DateUtil.NOCHAR_PATTERN2)+"'");
                        }else{
                            values.append(",''");
                        }
                    }else if(key.equalsIgnoreCase("A000_A0108")){
                        fields.append(",nl");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0134")){
                        fields.append(",cjgzsj");
                        if(value.toString().equals("")==false){
                            values.append(",'"+ DateUtil.formatDateByFormat((Date) value,DateUtil.NOCHAR_PATTERN2)+"'");
                        }else{
                            values.append(",''");
                        }
                    }else if(key.equalsIgnoreCase("A000_A0144")){
                        fields.append(",rdsj");
                        if(value.toString().equals("")==false){
                            values.append(",'"+ DateUtil.formatDateByFormat((Date) value,DateUtil.NOCHAR_PATTERN2)+"'");
                        }else{
                            values.append(",''");
                        }
                    }else if(key.equalsIgnoreCase("A000_A0127_SHOW")){
                        fields.append(",jkzk");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0601_SHOW")){
                        fields.append(",zyjszw");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0187A")){
                        fields.append(",zytc");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0801_SHOW")){
                        fields.append(",qrzxl");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0901_SHOW")){
                        fields.append(",qrzxw");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0814A")){
                        fields.append(",qrz_byyx");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0824A")){
                        fields.append(",qrz_zy");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0838_SHOW")){
                        fields.append(",zzxl");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0928_SHOW")){
                        fields.append(",zzxw");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0814D")){
                        fields.append(",zz_byyx");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0824B")){
                        fields.append(",zz_zy");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_A0253B")){
                        fields.append(",xrzjsj");
                        if(value.toString().equals("")==false){
                            values.append(",'"+ DateUtil.formatDateByFormat((Date) value,DateUtil.NOCHAR_PATTERN2)+"'");
                        }else{
                            values.append(",''");
                        }
                    }else if(key.equalsIgnoreCase("A000_A0192A")){
                        fields.append(",xrzw");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_JCQK")){
                        fields.append(",jcqk_str");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A000_NDKHJG")){
                        fields.append(",khjg_str");
                        values.append(",'"+value+"'");
                    }



                }
                //找到对应的工作经历
                String gzjlSql = " select * from a043 where a043.personcode = '"+personCode+"' ";
                List<Map<String, Object>> gzjlList = queryRunner.query(conn, gzjlSql, new MapListHandler(),(Object[]) null);
                if(gzjlList.size()>0){
                    Map<String,Object> gzjlMap = gzjlList.get(0);
                    for (Iterator<String> it = gzjlMap.keySet().iterator(); it.hasNext();) {
                        String key = it.next();
                        Object value = gzjlMap.get(key)==null?"":gzjlMap.get(key);
                        if(key.equalsIgnoreCase("A043_A4401")){
                            fields.append(",gzjl_str");
                            if(value.toString().equals("")==false){
                                String gzjl = value.toString().replace("~", "-").replace("{","(").replace("}",")");
                                values.append(",'"+gzjl+"'");
                            }else{
                                values.append(",''");
                            }
                        }
                    }
                }
                //找到对应的照片
                String zpSql = " select  * FROM APhoto where APhoto.personcode ='"+personCode+"' ";
                List<Map<String, Object>> zpList = queryRunner.query(conn, zpSql, new MapListHandler(),(Object[]) null);
                if(zpList.size()>0){
                    Map<String,Object> zpMap = zpList.get(0);
                    for (Iterator<String> it = zpMap.keySet().iterator(); it.hasNext();) {
                        String key = it.next();
                        Object value = zpMap.get(key);
                        if(key.equalsIgnoreCase("Ole1")){
                            if(value!=null){
                                byte[] bytes = (byte[])value;
                                if(bytes.length>0){
                                    try {
                                        String imageRealPath = photoStoreDir +personCode+".jpg";
                                        OutputStream fos= new FileOutputStream(imageRealPath);
                                        fos.write(bytes);
                                        fos.close();
                                        fields.append(",zp_path");
                                        values.append(",'"+AppAsetA01Service.ZP_PATH+personCode+".jpg'");
                                    } catch (Exception e1) {
                                        //e1.printStackTrace();
                                    }
                                    bytes=null;
                                }
                            }
                        }
                    }
                }
                fields.append(",a01_px");
                values.append(","+order+"");
                values.append(")");
                List<Object> paramList = new ArrayList<Object>();

                this.appAsetA01Dao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;
            }
        }

        DbUtils.close(conn);
        return order;
    }



}
