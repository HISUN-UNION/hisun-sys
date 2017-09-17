package com.hisun.saas.zzb.app.console.shpc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;
import com.hisun.saas.zzb.app.console.util.BeanTrans;
import com.hisun.util.DateUtil;
import com.hisun.util.WordUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhouying on 2017/9/8.
 */
@Controller
@RequestMapping("/zzb/app/console/Sha01")
public class Sha01Controller extends BaseController {
    @Autowired
    private ShpcService shpcService;

    @Autowired
    private Sha01Service sha01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest req,@RequestParam(value="shpcId")String shpcId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" shpc.id = :shpcId", "shpcId", shpcId));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
//            CommonOrderBy orderBy = new CommonOrderBy();
//            orderBy.add(CommonOrder.desc("updateDate"));

            Long total = this.sha01Service.count(query);
            List<Sha01> sha01s = this.sha01Service.list(query, null, pageNum,
                    pageSize);
            List<Sha01Vo> shpcVos = new ArrayList<Sha01Vo>();
            if (sha01s != null) {// entity ==> vo
                for (Sha01 sha01 : sha01s) {
                    Sha01Vo vo = new Sha01Vo();
                    BeanUtils.copyProperties(vo, sha01);
                    shpcVos.add(vo);
                }
            }
            PagerVo<Sha01Vo> pager = new PagerVo<Sha01Vo>(shpcVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("shpcId", shpcId);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/Sha01/list", map);
    }

    @RequestMapping(value="/ajax/execute")
    public @ResponseBody
    Map<String,Object> importExcel(String shpcId, String token, @RequestParam(value="attachFile",required=false) MultipartFile file, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
                String fileDir = uploadAbsolutePath + "/sha01";
                File _fileDir = new File(fileDir);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                String savePath = fileDir + "/" + DateUtil.formatDateByFormat(new Date(),"yyyyMMddHHmmssSSS")+"_"+fileName;

                try {
                    FileOutputStream fos = new FileOutputStream(new File(savePath));
                    fos.write(file.getBytes());
                    fos.flush();
                    fos.close();

                    //处理上传文件
                    //先将word转成Map
                    String tmplateWordPath = fileDir+File.separator+"sha01.docx";
                            //this.getClass().getClassLoader().getResource("sha01.docx").getPath();
                    WordUtil wordUtil = WordUtil.newInstance();

                    Map<String,String> dataMap = wordUtil.convertMapByTemplate( savePath, tmplateWordPath,"");

                    sha01Service.saveFromWordDataMap(userLoginDetails.getTenant(),dataMap,shpcId);



                    Shpc shpc = this.shpcService.getByPK(shpcId);
                    if (shpc != null) {
                        shpc.setFilePath(savePath);
                        BeanTrans.setBaseProperties(shpc, userLoginDetails, "update");
                        this.shpcService.update(shpc);
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
}
