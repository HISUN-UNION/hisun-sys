package com.hisun.saas.zzb.app.console.gendata.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.gendata.vo.GendataVo;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    @RequestMapping(value = "/")
    public ModelAndView list(){
        return new ModelAndView("saas/zzb/app/console/unuse");
    }


    @RequestMapping(value = "/generator")
    public ModelAndView generator(HttpServletRequest request) throws Exception{

        String pcs = request.getParameter("pcs");
        String tjs = request.getParameter("tjs");

        String appDataPath=resourcesProperties.getProperty("upload.absolute.path")+GendataService.DATA_PATH;
        File appDataDir = new File(appDataPath);
        if(appDataDir.exists()==false){
            appDataDir.mkdirs();
        }
        Map<String,String> map = new HashMap<String,String>();
        if(pcs!=null &&pcs.length()>0){
            map.put(GendataVo.SHPC_DATA,pcs);
        }
        if(tjs!=null &&tjs.length()>0){
            map.put(GendataVo.GBTJ_DATA,tjs);
        }

        this.gendataService.genAppData(map,appDataPath);
        return new ModelAndView("saas/zzb/app/console/unuse");
    }


}
