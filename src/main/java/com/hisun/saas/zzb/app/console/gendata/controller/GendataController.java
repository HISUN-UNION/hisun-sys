package com.hisun.saas.zzb.app.console.gendata.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.exception.GenericException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Properties;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gendata")
public class GendataController extends BaseController{

    @Resource(name="resourcesProperties")
    private Properties resourcesProperties;

    @RequestMapping(value = "/")
    public ModelAndView list(){
        return new ModelAndView("saas/zzb/app/console/unuse");
    }


    @RequestMapping(value = "/generator")
    public ModelAndView generator(HttpServletRequest request) throws GenericException{

        String pcs = request.getParameter("pcs");
        String tjs = request.getParameter("tjs");

        String appDataPath=resourcesProperties.getProperty("upload.absolute.path");
        appDataPath = appDataPath+ File.separator+"/appdata/";

        File appDataDir = new File(appDataPath);
        if(appDataDir.exists()==false){
            appDataDir.mkdirs();
        }



        return new ModelAndView("saas/zzb/app/console/unuse");
    }


}
