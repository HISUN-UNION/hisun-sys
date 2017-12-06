package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.aspose.words.*;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01Dao;
import com.hisun.saas.zzb.app.console.gbmc.entity.*;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gbrmspbService;
import com.hisun.saas.zzb.app.console.util.GzjlUtil;
import com.hisun.util.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbMcA01ServiceImpl extends BaseServiceImpl<GbMcA01,String> implements GbMcA01Service {

    private GbMcA01Dao gbMcA01Dao;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<GbMcA01, String> gbMcA01Dao) {
        this.baseDao = gbMcA01Dao;
        this.gbMcA01Dao = (GbMcA01Dao) gbMcA01Dao;
    }


    public void saveFromWordDataMap(Map<String, String> dataMap, GbMcB01 gbMcB01){
        this.gbMcA01Dao.saveFromWordDataMap(dataMap,gbMcB01);
    }



    public void updateA01FromYwJson(String gbmcId,String ywJsonPath, String photoPath) throws Exception{
        //JSON及照片临时目录
        File jsonFiles = new File(ywJsonPath);
        File photos = new File(photoPath);
        if(jsonFiles!=null) {
            for (File jsonFile : jsonFiles.listFiles()) {
                if(jsonFile.isDirectory()
                        || jsonFile.getName().toLowerCase().endsWith("json")==false){
                    continue;
                }
                String json = FileUtils.readFileToString(jsonFile);
                JacksonUtil util = new JacksonUtil();
                List<Map<String,String>> gbrmsbpDataList = util.fromJson(json,List.class);
                for(Map<String,String> dataMap : gbrmsbpDataList){
                    String xm = com.hisun.util.StringUtils.trimBlank(dataMap.get("name"));
                    if(xm!=null){
                        //在A01表中找到对应的记录
                        CommonConditionQuery query = new CommonConditionQuery();
                        query.add(CommonRestrictions.and(" xm like :xmQuery", "xmQuery", "%"+ xm+ "%"));
                        query.add(CommonRestrictions.and(" gbMc.id = :gbmcId", "gbmcId",  gbmcId));
                        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
                        List<GbMcA01> a01s = this.list(query,null);
                        if(a01s!=null && a01s.size()==1){//这里==1的目的是确保在同名情况下不写入数据
                            GbMcA01 a01 = a01s.get(0);
                            //处理干部任免审批表数据
                            GbMcA01gbrmspb gbMcA01gbrmspb = new GbMcA01gbrmspb(dataMap);
                            a01.addGbrmspb(gbMcA01gbrmspb);
                            //处理工作经历数据
                            Object obj = dataMap.get("intro");
                            if(obj instanceof String){
                                List<String> gzjlList = GzjlUtil.matchGzjlStr((String)obj);
                                int i=1;
                                for(String gzjl : gzjlList){
                                    GbMcA01gzjl gbMcA01gzjl = new GbMcA01gzjl(gzjl,i);
                                    a01.addGzjl(gbMcA01gzjl);
                                    i++;
                                }
                            }
                            //处理社会关系数据,最多10条数据
                            for(int i=0;i<10;i++){
                                GbMcA01shgx gbMcA01shgx = new GbMcA01shgx(dataMap,i);
                                if(gbMcA01shgx.getXm()!=null){
                                    a01.addShgx(gbMcA01shgx);
                                }
                            }
                            //处理照片
                            String photoFileName = dataMap.get("photos");
                            if(photoFileName!=null && StringUtils.isEmpty(photoFileName)==false){
                                String photoStorePath = GbMcA01Service.ATTS_PATH+ UUIDUtil.getUUID()+".jpg";
                                String photoRealStorePath = uploadAbsolutePath+photoStorePath;
                                if(photos!=null) {
                                    for (File photo : photos.listFiles()) {
                                        if (photo.isDirectory()
                                                || photo.getName().toLowerCase().endsWith("jpg") == false) {
                                            continue;
                                        }
                                        if(photo.getName().equals(photoFileName)){
                                            FileUtils.copyFile(photo,new File(photoRealStorePath));
                                            a01.setZppath(photoStorePath);
                                            gbMcA01gbrmspb.setZppath(photoStorePath);
                                            break;
                                        }
                                    }

                                }

                            }
                            //生成Word并转换成pdf存入gbrmspb
                            this.buildWordAndUpateGbrmspb(a01);
                            this.update(a01);
                        }
                    }
                }
            }
        }
        FileUtils.deleteDirectory(jsonFiles);
        FileUtils.deleteDirectory(photos);

    }

    private void buildWordAndUpateGbrmspb(GbMcA01 gbMcA01) throws Exception{
        if(gbMcA01.getGbMca01gbrmspbs().size()>0){
            WordUtil wordUtil = WordUtil.newInstance();
            Document gbrmspbTemplateDoc = wordUtil.read(uploadAbsolutePath+GbMcA01gbrmspbService.GBRMSPB_DOC_TEMPLATE);
            GbMcA01gbrmspb gbMcA01gbrmspb = gbMcA01.getGbMca01gbrmspbs().get(0);
            Map<String,String> gbrmspbFieldMap = gbMcA01gbrmspb.toSqlFieldMap();
            NodeCollection templateCells = gbrmspbTemplateDoc.getChildNodes(NodeType.CELL, true);
            for(Iterator<Cell> cellIterator = templateCells.iterator(); cellIterator.hasNext();){
                String trimText = wordUtil.trim(cellIterator.next().getText());
                if (trimText.startsWith(WordUtil.dataPrefix)) {
                    String field = wordUtil.getSqlField(trimText).toLowerCase();
                    String value = gbrmspbFieldMap.get(field);
                    if(value!=null){
                        gbrmspbTemplateDoc.getRange().replace(trimText,value,
                                new FindReplaceOptions(FindReplaceDirection.FORWARD));
                    }else{
                        gbrmspbTemplateDoc.getRange().replace(trimText,"",
                                new FindReplaceOptions(FindReplaceDirection.FORWARD));
                    }
                }
            }
            String saveWordPath = GbMcA01gbrmspbService.ATTS_PATH+UUIDUtil.getUUID()+".docx";
            gbrmspbTemplateDoc.save(uploadAbsolutePath+saveWordPath);
            gbMcA01gbrmspb.setFilepath(saveWordPath);

            String pdfPath = GbMcA01gbrmspbService.ATTS_PATH+UUIDUtil.getUUID()+".pdf";
            String pdfRealPath = uploadAbsolutePath+pdfPath;
            WordConvertUtil.newInstance().convert(uploadAbsolutePath+saveWordPath,pdfRealPath,WordConvertUtil.PDF);
            gbMcA01gbrmspb.setFile2imgPath(pdfPath);
        }
    }

}
