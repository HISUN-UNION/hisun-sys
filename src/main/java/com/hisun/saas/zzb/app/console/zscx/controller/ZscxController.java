package com.hisun.saas.zzb.app.console.zscx.controller;

import com.hisun.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/zscx")
public class ZscxController extends BaseController {


    @RequestMapping(value = "/")
    public ModelAndView list(){
        return new ModelAndView("saas/zzb/app/console/zscx/zscxManage");
    }

}
