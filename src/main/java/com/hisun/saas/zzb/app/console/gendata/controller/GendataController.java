package com.hisun.saas.zzb.app.console.gendata.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.ErrorMsgShowException;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcVo;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gbtj.service.GbtjService;
import com.hisun.saas.zzb.app.console.gbtj.vo.GbtjVo;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.gendata.vo.GendataVo;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;
import com.hisun.util.DateUtil;
import com.hisun.util.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gendata")
public class GendataController extends BaseController{

    @Autowired
    private GendataService gendataService;

    @Resource(name="resourcesProperties")
    private Properties resourcesProperties;
    @Resource
    private GbtjService gbtjService;
    @Autowired
    private GbMcService gbMcService;
    @Autowired
    private ShpcService shpcService;
    @RequestMapping(value = "/")
    public ModelAndView list(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //获取会议研究列表数据
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            query.add(CommonRestrictions.and(" shZt = :shZt", "shZt", 0));
            Long total = this.shpcService.count(query);
            List<Shpc> shpcs = this.shpcService.list(query, null);
            List<ShpcVo> shpcVos = new ArrayList<ShpcVo>();
            if (shpcs != null) {// entity ==> vo
                for (Shpc shpc : shpcs) {
                    ShpcVo vo = new ShpcVo();
                    BeanUtils.copyProperties(vo, shpc);
                    vo.setPcsjValue(DateUtil.formatDateByFormat(shpc.getPcsj(), "yyyyMMdd"));
                    vo.setA01Count(shpc.getSha01s().size());
                    shpcVos.add(vo);
                }
            }

            //获取干部名册数据
            query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            total = this.shpcService.count(query);
            List<GbMc> gbmcs = this.gbMcService.list(query, null);
            List<GbMcVo> gbmcVos = new ArrayList<GbMcVo>();
            if (gbmcs != null) {// entity ==> vo
                for (GbMc gbMc : gbmcs) {
                    GbMcVo vo = new GbMcVo();
                    BeanUtils.copyProperties(vo, gbMc);
                    gbmcVos.add(vo);
                }
            }

            //获取队伍统计数据
            query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            total = this.shpcService.count(query);
            List<Gbtj> gbtjs = this.gbtjService.list(query, null);
            List<GbtjVo> gbtjVos = new ArrayList<GbtjVo>();
            if (gbmcs != null) {// entity ==> vo
                for (Gbtj gbmc : gbtjs) {
                    GbtjVo vo = new GbtjVo();
                    BeanUtils.copyProperties(vo, gbmc);
                    gbtjVos.add(vo);
                }
            }
            map.put("shpcVos", shpcVos);
            map.put("gbmcVos", gbmcVos);
            map.put("gbtjVos", gbtjVos);
        }catch (Exception e) {
            throw new GenericException(e);
        }

        return new ModelAndView("saas/zzb/app/console/gendata/list",map);
    }


    @RequestMapping(value = "/generator")
    public @ResponseBody Map<String,Object> generator(HttpServletResponse response, HttpServletRequest request) throws Exception{
        Map<String,Object> rsmap = new HashMap<String,Object>();
        try{
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            String checkBoxTypeValues = request.getParameter("checkBoxTypeValues")==null?"":request.getParameter("checkBoxTypeValues").toString();//选择需要导出的类型

            String pcs = request.getParameter("checkHyyjValues")==null?"":request.getParameter("checkHyyjValues").toString();//会议研究ID
            String gbmcs = request.getParameter("checkGbmcValues")==null?"":request.getParameter("checkGbmcValues").toString();//干部名册ID
            String tjs = request.getParameter("checkGbtjValues")==null?"":request.getParameter("checkGbtjValues").toString();//干部统计ID


            String appDataPath=resourcesProperties.getProperty("upload.absolute.path")+GendataService.DATA_PATH;
            File appDataDir = new File(appDataPath);
            if(appDataDir.exists()==false){
                appDataDir.mkdirs();
            }
            Map<String,String> map = new HashMap<String,String>();
            if(pcs!=null &&pcs.length()>0){
                map.put(GendataVo.SHPC_DATA,pcs);
            }

            if(gbmcs!=null &&gbmcs.length()>0){
                map.put(GendataVo.GBMC_DATA,gbmcs);
            }

            if(tjs!=null &&tjs.length()>0){
                map.put(GendataVo.GBTJ_DATA,tjs);
            }
            Gendata gendata = new Gendata();
            gendata.setTenant(userLoginDetails.getTenant());
            String id = this.gendataService.saveAppData(gendata,map,appDataPath);
            rsmap.put("gendataId", id);
        }catch(Exception e){
            logger.error(e, e);
            rsmap.put("success", false);
            rsmap.put("message", "系统错误，请联系管理员!");
            return rsmap;
        }
        rsmap.put("success", true);

        return rsmap;
    }

    @RequestMapping(value="/zip/down")
    public void zipDown(String id,HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String zipPath = this.gendataService.getByPK(id).getPath();
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "attachment;fileName="+encode(GendataService.DATA_PACKET_NAME+".zip"));
        OutputStream output=resp.getOutputStream();
        byte[] b= FileUtils.readFileToByteArray(new File(zipPath));
        output.write(b);
        output.flush();
        output.close();

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
