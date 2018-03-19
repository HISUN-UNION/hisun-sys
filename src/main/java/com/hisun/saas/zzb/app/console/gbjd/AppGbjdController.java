package com.hisun.saas.zzb.app.console.gbjd;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA01Vo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbjd")
public class AppGbjdController extends BaseController{


    //===============base
    @RequestMapping(value = "/base")
    public ModelAndView base(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 1, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/list",map);
    }
    @RequestMapping(value = "/addManage")
    public ModelAndView addManage(String b01Id) throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        return new ModelAndView("/saas/zzb/app/console/gbjd/base/addManage", map);
    }
    @RequestMapping(value = "/ajax/editBase")
    public ModelAndView editBase(){
        return new ModelAndView("saas/zzb/app/console/gbjd/base/addBase");
    }

    @RequestMapping(value = "/ajax/sjccgbGrList")
    public ModelAndView sjccgbGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/sjccgbGrList",map);
    }
    @RequestMapping(value = "/ajax/sjgbGrList")
    public ModelAndView sjgbGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/sjgbGrList",map);
    }
    @RequestMapping(value = "/ajax/xfjbGrList")
    public ModelAndView xfjbGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/xfjbGrList",map);
    }

    @RequestMapping(value = "/ajax/ssyjList")
    public ModelAndView ssyjList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/ssyj/list",map);
    }
    @RequestMapping(value = "/ajax/zrzjGrList")
    public ModelAndView zrzjGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/zrzjGrList",map);
    }
    @RequestMapping(value = "/ajax/wzGrList")
    public ModelAndView wzGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/wzGrList",map);
    }
    //=========================

    @RequestMapping(value = "/grsxch/sjcc")
    public ModelAndView sjcc(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/list", map);
    }

    @RequestMapping(value = "/grsxch/sjccgbList")
    public ModelAndView sjccgbList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/ccgbList",map);
    }
    @RequestMapping(value = "/grsxch/sjccgbAdd")
    public ModelAndView sjccgbAdd(){
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/sjccgbAdd");
    }
    @RequestMapping(value = "/grsxch/zdch")
    public ModelAndView zdch(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/zdch/list",map);
    }
    @RequestMapping(value = "/grsxch/zdchgbList")
    public ModelAndView zdchgbList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/zdchgbList",map);
    }
    @RequestMapping(value = "/grsxch/zdchgbAdd")
    public ModelAndView zdchgbAdd(){
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/zdchgbAdd");
    }

    @RequestMapping(value = "/jjzrsj")
    public ModelAndView jjzrsj(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/jjzrsj/list",map);
    }
    @RequestMapping(value = "/jjzrsj/sjgbList")
    public ModelAndView sjgbList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/jjzrsj/sjgbList",map);
    }

    @RequestMapping(value = "/jjzrsj/edit")
    public ModelAndView jjzrsjEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/jjzrsj/edit");
    }


    @RequestMapping(value = "/xfjb")
    public ModelAndView xfjb(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/xfjb/list",map);
    }

    @RequestMapping(value = "/xfjb/edit")
    public ModelAndView xfjbEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/xfjb/edit");
    }

    @RequestMapping(value = "/ybglpy")
    public ModelAndView ybglpy(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/ybglpy/list",map);
    }
    @RequestMapping(value = "/ybglpy/pyjgList")
    public ModelAndView pyjgList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/ybglpy/pyjgList",map);
    }
    @RequestMapping(value = "/ybglpy/edit")
    public ModelAndView ybglpyEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/ybglpy/edit");
    }
    @RequestMapping(value = "/zrzj")
    public ModelAndView zrzj(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/zrzj/list",map);
    }
    @RequestMapping(value = "/zrzj/edit")
    public ModelAndView zrzjEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/zrzj/edit");
    }

    @RequestMapping(value = "/wz/dzzwz")
    public ModelAndView dzzwz(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/wz/dzzwz/list",map);
    }
    @RequestMapping(value = "/wz/ldgbwz")
    public ModelAndView wz(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 2, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/wz/ldgbwz/list",map);
    }
    @RequestMapping(value = "/wz/dzzwz/edit")
    public ModelAndView dzzwzEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/wz/dzzwz/edit");
    }
    @RequestMapping(value = "/wz/ldgbwz/edit")
    public ModelAndView ldgbwzEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/wz/ldgbwz/edit");
    }
    @RequestMapping(value = "/ajax/viewGbjd")
    public ModelAndView viewGbjd(){
        return new ModelAndView("saas/zzb/app/console/gbjd/viewGbjd");
    }
}
