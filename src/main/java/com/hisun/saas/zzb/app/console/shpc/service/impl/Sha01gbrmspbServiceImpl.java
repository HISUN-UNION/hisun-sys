package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01gbrmspbDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gzjl;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gbrmspbService;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gzjlService;
import com.hisun.util.Pdf2ImgUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordConvertUtil;
import com.hisun.util.WordUtil;
import org.apache.commons.io.FileUtils;
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
public class Sha01gbrmspbServiceImpl extends BaseServiceImpl<Sha01gbrmspb, String> implements Sha01gbrmspbService {

    private Sha01gbrmspbDao sha01gbrmspbDao;
    @Resource
    private Sha01gzjlService sha01gzjlService;
    @Resource
    private Sha01Service sha01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<Sha01gbrmspb, String> sha01gbrmspbDao) {
        this.baseDao = sha01gbrmspbDao;
        this.sha01gbrmspbDao = (Sha01gbrmspbDao) sha01gbrmspbDao;
    }


    public String saveFromWord(Sha01gbrmspb gbrmspb, String wordsourcePath, String templatePath) throws Exception {
        Map<String, String> dataMap = WordUtil.newInstance().convertMapByTemplate(wordsourcePath, templatePath);
        this.dealAtts(gbrmspb,wordsourcePath);
        this.dealPhoto(gbrmspb,wordsourcePath);
        //先删除原有的干部任免审批表
        Sha01 sha01 = gbrmspb.getSha01();
        if(sha01.getGbrmspbs()!=null && sha01.getGbrmspbs().size()>0){
            List<Sha01gbrmspb> tempSha01gbrmspbs = new ArrayList<Sha01gbrmspb>();
            for(Sha01gbrmspb gb :sha01.getGbrmspbs()){
                tempSha01gbrmspbs.add(gb);
            }
            for(Sha01gbrmspb gb :tempSha01gbrmspbs){
                sha01.getGbrmspbs().remove(gb);
                gb.setSha01(null);
                this.sha01gbrmspbDao.delete(gb);
            }
            //由于工作经历也是导入的,所以一并删除
            List<Sha01gzjl> tempSha01gzjls = new ArrayList<Sha01gzjl>();
            for(Sha01gzjl gzjl :sha01.getGzjls()){
                tempSha01gzjls.add(gzjl);
            }
            for(Sha01gzjl gzjl :tempSha01gzjls){
                sha01.getGzjls().remove(gzjl);
                gzjl.setSha01(null);
                this.sha01gzjlService.delete(gzjl);
            }
        }
        String id = this.sha01gbrmspbDao.saveFromWord(gbrmspb,dataMap);

        this.sha01gzjlService.saveFromWord(gbrmspb.getSha01(),dataMap);
        return id;
    }

    private void dealPhoto(Sha01gbrmspb gbrmspb,String wordsourcePath) throws Exception{
        //临时方式处理照片(取得当前文档中所有照片)
        List<byte[]> imgs = WordUtil.newInstance().extractImages(wordsourcePath);
        if(imgs!=null&&imgs.size()>0){
            File file = new File(uploadAbsolutePath+Sha01Service.ATTS_PATH);
            if(file.exists()==false){
                file.mkdirs();
            }
            String photoPath = Sha01Service.ATTS_PATH+ UUIDUtil.getUUID()+".jpg";
            String photoRealPath = uploadAbsolutePath+photoPath;
            FileOutputStream photofos = new FileOutputStream(new File(photoRealPath));
            photofos.write(imgs.get(0));
            photofos.flush();
            photofos.close();
            gbrmspb.setZppath(photoPath);
            Sha01 sha01 = gbrmspb.getSha01();
            sha01.setZppath(photoPath);
            this.sha01Service.update(sha01);
        }
    }

    private void dealAtts(Sha01gbrmspb gbrmspb,String wordsourcePath)throws Exception{
        String pdfPath = Sha01gbrmspbService.ATTS_PATH+UUIDUtil.getUUID()+".pdf";
        String pdfRealPath = uploadAbsolutePath+pdfPath;
        WordConvertUtil.newInstance().convert(wordsourcePath,pdfRealPath,WordConvertUtil.PDF);
        gbrmspb.setFile2imgPath(pdfPath);
    }

}