package com.hisun.saas.zzb.app.console.shpc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01dascqk;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01grzdsx;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01dascqkService;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gbrmspbService;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01grzdsxService;
import com.hisun.util.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Controller
@RequestMapping("/zzb/app/Sha01/dascqk")
public class Sha01dascqkController extends BaseController {
    @Autowired
    private Sha01Service sha01Service;

    @Autowired
    private Sha01dascqkService sha01dascqkService;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping(value="/ajax/uploadFile")
    public @ResponseBody
    Map<String,Object> upload(String sha01Id, @RequestParam(value="dascqkFile",required=false) MultipartFile file,
                                   HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> map = new HashMap<String,Object>();
        if(file==null || file.isEmpty()){
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;
        }

        try{
            String fileName = file.getOriginalFilename();
            if(fileName.endsWith(".doc") ||fileName.endsWith(".DOC") ||fileName.endsWith(".docx") ||fileName.endsWith(".DOCX") ){
                String fileDir = uploadAbsolutePath + Sha01dascqkService.ATTS_PATH;
                File _fileDir = new File(fileDir);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }

                String savePath = Sha01dascqkService.ATTS_PATH+UUIDUtil.getUUID()+"_"+fileName;
                String saveRealPath = uploadAbsolutePath + savePath;
                try {
                    //上传附件
                    FileOutputStream fos = new FileOutputStream(new File(saveRealPath));
                    fos.write(file.getBytes());
                    fos.flush();
                    fos.close();
                    //将上传附件转换成PDF
                    String pdfPath = Sha01dascqkService.ATTS_PATH+ UUIDUtil.getUUID()+".pdf";
                    String pdfRealPath = uploadAbsolutePath+ pdfPath;
                    //String imgPath = fileDir+UUIDUtil.getUUID()+".jpg";
                    //先将其转PDF
                    WordConvertUtil.newInstance().convert(saveRealPath,pdfRealPath,WordConvertUtil.PDF);
                    //再将其转成图片
                   // Pdf2ImgUtil.toImg(pdfPath,imgPath);
                    Sha01 sha01 = this.sha01Service.getByPK(sha01Id);
                    if(sha01.getDascqks()!=null &&sha01.getDascqks().size()>0){//修改
                        Sha01dascqk sha01dascqk = sha01.getDascqks().get(0);
                        sha01dascqk.setPath(savePath);
                        sha01dascqk.setSha01(sha01);
                        sha01dascqk.setFile2imgPath(pdfPath);
                        this.sha01dascqkService.update(sha01dascqk);
                    }else{//创建
                        Sha01dascqk sha01dascqk = new Sha01dascqk();
                        sha01dascqk.setPath(savePath);
                        sha01dascqk.setSha01(sha01);
                        sha01dascqk.setFile2imgPath(pdfPath);
                        this.sha01dascqkService.save(sha01dascqk);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new GenericException(e);
                }
            }else{
                map.put("code", -1);
                map.put("message", "请上传word");
                return map;
            }
        }catch(Exception e){
            map.put("code", -1);
            map.put("message", "读取文件错误，请检查word是否能正确打开");
            return map;
        }
        try{

        }catch(GenericException e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", e.getMessage());
            return map;
        }catch(Exception e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", "系统错误，请联系管理员");
            return map;
        }
        map.put("code", 1);
        return map;
    }


    /**
     *
     * @param shpcId
     * @param uploadMatchingMode //批量上传匹配方式 0按序号匹配 1按姓名匹配
     * @param file
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/ajax/batch/upload")
    public @ResponseBody
    Map<String,Object> batchUpload(String shpcId,String uploadMatchingMode,@RequestParam(value="dascqkFile",required=false) MultipartFile file,
                                   HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> map = new HashMap<String,Object>();
        if(file==null || file.isEmpty()){
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;

        }
        try{
            String fileName = file.getOriginalFilename();
            if(fileName.toLowerCase().endsWith(".zip")){
                String dascqkbpAttsPath = uploadAbsolutePath+ Sha01dascqkService.ATTS_PATH;
                File _fileDir = new File(dascqkbpAttsPath);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                //原zip存储路径
                String zipFilePath = dascqkbpAttsPath + UUIDUtil.getUUID()+".zip";
                File zipFile = new File(zipFilePath);
                FileOutputStream fos = new FileOutputStream(zipFile);
                fos.write(file.getBytes());
                fos.flush();
                fos.close();

                String tmpFilePath =  dascqkbpAttsPath+UUIDUtil.getUUID()+File.separator;
                //解压到临时目录
                CompressUtil.unzip(zipFilePath,tmpFilePath);
                //循环目录下的文件,如果在当前批次下找到对应名字的干部,则附加到当前干部下
                File tempFiles = new File(tmpFilePath);
                if(tempFiles!=null){
                    for(File f : tempFiles.listFiles()){
                        if(f.isDirectory()) continue;//如果是目录则跳过
                        String fname = f.getName();
                        String xm = fname.substring(0,fname.lastIndexOf("."));
                        CommonConditionQuery query = new CommonConditionQuery();
                        query.add(CommonRestrictions.and(" Sha01.xm like :xm ", "xm", "%"+xm+"%"));
                        query.add(CommonRestrictions.and(" Sha01.shpc.id = :shpc ", "shpc", shpcId));
                        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
                        List<Sha01> sha01s = this.sha01Service.list(query,null);
                        if(sha01s!=null && sha01s.size()>0){
                            String ext = f.getName().substring(f.getName().lastIndexOf("."));
                            String savePath = Sha01dascqkService.ATTS_PATH+UUIDUtil.getUUID()+ext;
                            String saveRealPath = uploadAbsolutePath+savePath;
                            File desFile = new File(saveRealPath);
                            FileUtils.copyFile(f,desFile);
                            //处理
                            String pdfPath = Sha01dascqkService.ATTS_PATH+ UUIDUtil.getUUID()+".pdf";
                            String pdfRealPath = uploadAbsolutePath+pdfPath;
                            WordConvertUtil.newInstance().convert(saveRealPath,pdfRealPath,WordConvertUtil.PDF);
                            Sha01 sha01 = sha01s.get(0);
                            if(sha01.getDascqks()!=null &&sha01.getDascqks().size()>0){//修改
                                Sha01dascqk sha01dascqk = sha01.getDascqks().get(0);
                                sha01dascqk.setPath(savePath);
                                sha01dascqk.setSha01(sha01);
                                sha01dascqk.setFile2imgPath(pdfPath);
                                this.sha01dascqkService.update(sha01dascqk);
                            }else{//创建
                                Sha01dascqk sha01dascqk = new Sha01dascqk();
                                sha01dascqk.setPath(savePath);
                                sha01dascqk.setSha01(sha01);
                                sha01dascqk.setFile2imgPath(pdfPath);
                                this.sha01dascqkService.save(sha01dascqk);
                            }
                        }
                    }
                }
            //删除临时文件目录
            FileUtils.deleteQuietly(tempFiles);
            //删除zip文件
            FileUtils.forceDelete(zipFile);
            }else{
                map.put("code", -1);
                map.put("message", "请上传ZIP!");
                return map;
            }
        }catch(Exception e){
            e.printStackTrace();

            map.put("code", -1);
            map.put("message", "读取文件错误!");
            return map;
        }
        try{

        }catch(GenericException e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", e.getMessage());
            return map;
        }catch(Exception e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", "系统错误，请联系管理员");
            return map;
        }
        map.put("code", 1);
        return map;
    }





    @RequestMapping(value="/ajax/down")
    public void templateDown(String sha01Id,HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Sha01 sha01 = this.sha01Service.getByPK(sha01Id);
        if(sha01.getDascqks()!=null &&sha01.getDascqks().size()>0){//修改
            Sha01dascqk sha01dascqk = sha01.getDascqks().get(0);
            resp.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            resp.setHeader("Content-Disposition", "attachment;fileName="+encode(sha01dascqk.getPath().substring(sha01dascqk.getPath().lastIndexOf(File.separator)+1)));
            OutputStream output=resp.getOutputStream();
            byte[] b= FileUtils.readFileToByteArray(new File(uploadAbsolutePath+sha01dascqk.getPath()));
            output.write(b);
            output.flush();
            output.close();
        }

    }
    private String encode(String filename) throws UnsupportedEncodingException {
        if (WebUtil.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        }
        return filename;
    }
    
}
