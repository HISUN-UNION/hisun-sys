package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01gbrmspbDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gbrmspb;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gzjl;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gbrmspbService;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gzjlService;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.util.Pdf2ImgUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordConvertUtil;
import com.hisun.util.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class GbMcA01gbrmspbServiceImpl extends BaseServiceImpl<GbMcA01gbrmspb, String> implements GbMcA01gbrmspbService {

    private GbMcA01gbrmspbDao gbMcA01gbrmspbDao;
    @Resource
    private GbMcA01gzjlService gbMcA01gzjlService;
    @Resource
    private GbMcA01Service gbMcA01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<GbMcA01gbrmspb, String> gbMcA01gbrmspbDao) {
        this.baseDao = gbMcA01gbrmspbDao;
        this.gbMcA01gbrmspbDao = (GbMcA01gbrmspbDao) gbMcA01gbrmspbDao;
    }


    public String saveFromWord(GbMcA01gbrmspb gbrmspb, String wordsourcePath, String templatePath) throws Exception {
        Map<String, String> dataMap = WordUtil.newInstance().convertMapByTemplate(wordsourcePath, templatePath);
        this.dealAtts(gbrmspb,wordsourcePath);
        this.dealPhoto(gbrmspb,wordsourcePath);
        //先删除原有的干部任免审批表
        GbMcA01 gbMcA01 = gbrmspb.getGbMcA01();
        if(gbMcA01.getGbMca01gbrmspbs()!=null && gbMcA01.getGbMca01gbrmspbs().size()>0){
            List<GbMcA01gbrmspb> tempGbMca01gbrmspbs = new ArrayList<GbMcA01gbrmspb>();
            for(GbMcA01gbrmspb gb :gbMcA01.getGbMca01gbrmspbs()){
                tempGbMca01gbrmspbs.add(gb);
            }
            for(GbMcA01gbrmspb gb :tempGbMca01gbrmspbs){
                gbMcA01.getGbMca01gbrmspbs().remove(gb);
                gb.setGbMcA01(null);
                this.gbMcA01gbrmspbDao.delete(gb);
            }
            //由于工作经历也是导入的,所以一并删除
            List<GbMcA01gzjl> tempGbMca01gzjls = new ArrayList<GbMcA01gzjl>();
            for(GbMcA01gzjl gzjl :gbMcA01.getGbMca01gzjls()){
                tempGbMca01gzjls.add(gzjl);
            }
            for(GbMcA01gzjl gzjl :tempGbMca01gzjls){
                gbMcA01.getGbMca01gzjls().remove(gzjl);
                gzjl.setGbMcA01(null);
                this.gbMcA01gzjlService.delete(gzjl);
            }
        }
        String id = this.gbMcA01gbrmspbDao.saveFromWord(gbrmspb,dataMap);
        this.gbMcA01gzjlService.saveFromWord(gbrmspb.getGbMcA01(),dataMap);
        return id;
    }

    private void dealPhoto(GbMcA01gbrmspb gbrmspb, String wordsourcePath) throws Exception{
        //临时方式处理照片(取得当前文档中所有照片)
        List<byte[]> imgs = WordUtil.newInstance().extractImages(wordsourcePath);
        if(imgs!=null&&imgs.size()>0){
            File file = new File(uploadAbsolutePath+Sha01Service.ATTS_PATH);
            if(file.exists()==false){
                file.mkdirs();
            }
            String photo = uploadAbsolutePath+Sha01Service.ATTS_PATH+ UUIDUtil.getUUID()+".jpg";
            FileOutputStream photofos = new FileOutputStream(new File(photo));
            photofos.write(imgs.get(0));
            photofos.flush();
            photofos.close();
            GbMcA01 gbMcA01 = gbrmspb.getGbMcA01();
            gbMcA01.setZppath(photo);
            this.gbMcA01Service.update(gbMcA01);
        }
    }

    private void dealAtts(GbMcA01gbrmspb gbrmspb, String wordsourcePath)throws Exception{
        //处理附件
        String pdfPath = uploadAbsolutePath+GbMcA01gbrmspbService.ATTS_PATH+UUIDUtil.getUUID()+".pdf";
        String imgPath = uploadAbsolutePath+GbMcA01gbrmspbService.ATTS_PATH+UUIDUtil.getUUID()+".jpg";
        //先将其转PDF
        WordConvertUtil.newInstance().convert(wordsourcePath,pdfPath,WordConvertUtil.PDF);
        //再将其转成图片
        Pdf2ImgUtil.toImg(pdfPath,imgPath);
        gbrmspb.setFile2imgPath(imgPath);
    }

}