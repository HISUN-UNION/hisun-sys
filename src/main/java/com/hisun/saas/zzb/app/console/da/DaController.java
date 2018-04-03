package com.hisun.saas.zzb.app.console.da;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA01Vo;
import com.hisun.saas.zzb.app.console.bset.vo.B01TreeVo;
import com.hisun.util.WebUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/a38")
public class DaController extends BaseController{
    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;
    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard(){
        return new ModelAndView("saas/zzb/app/console/a38/dashboard");
    }
    //===============base
    @RequestMapping(value = "/list")
    public ModelAndView list(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/list",map);
    }
    @RequestMapping(value = "/manage")
    public ModelAndView ryManage(String loadType){
        Map<String, Object> map = Maps.newHashMap();
        map.put("loadType",loadType);
        return new ModelAndView("saas/zzb/app/console/a38/manage/manage",map);
    }
    @RequestMapping(value = "/addDaBase")
    public ModelAndView addDaBase(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/addDaBase");
    }
    @RequestMapping(value = "/ajax/base")
    public ModelAndView base(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/base");
    }
    @RequestMapping(value = "/ajax/mlxxManage")
    public ModelAndView mlxxManage(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/mlxxManage");
    }
    @RequestMapping(value="/ajax/load/tree")
    public @ResponseBody
    Map<String,Object> loadTree(String loadType)throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<B01TreeVo> b01TreeVoList = new ArrayList<B01TreeVo>();
            B01TreeVo vo = new B01TreeVo();

            if(loadType!=null && loadType.equals("manage")){
                vo.setId("0");
                vo.setName("目录类型");
                vo.setpId("0");
                vo.setIsParent(true);
                vo.setDropInner(true);
                vo.setOpen(true);
                b01TreeVoList.add(vo);
            }
            vo = new B01TreeVo();
            vo.setId("1");
            vo.setName("1.简历材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            if(loadType!=null && loadType.equals("view")){
                vo = new B01TreeVo();
                vo.setId("1.1");
                vo.setName("干部履历表");
                vo.setpId("1");
                vo.setDataType("ml");
                vo.setOpen(true);
                b01TreeVoList.add(vo);
            }
            vo = new B01TreeVo();
            vo.setId("2");
            vo.setName("2.自传材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("3");
            vo.setName("3.鉴定、考核、考察材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("4");
            vo.setName("4.学历、学位、学绩、培训和专业技术情况材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(false);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("41");
            vo.setName("1.学历学位材料");
            vo.setpId("4");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("42");
            vo.setName("2.职业（任职）资格和评（聘）专业技术职务（职称）材料");
            vo.setpId("4");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("43");
            vo.setName("3.反映科研学术水平的材料");
            vo.setpId("4");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("44");
            vo.setName("4.培训材料");
            vo.setpId("4");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("5");
            vo.setName("5.政审材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("6");
            vo.setName("6.加入党团材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("7");
            vo.setName("7.奖励材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("8");
            vo.setName("8.处分材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("9");
            vo.setName("9.工资、任免、出国、代表大会等材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(false);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("91");
            vo.setName("1.工资");
            vo.setpId("9");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("92");
            vo.setName("2.任免");
            vo.setpId("9");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("93");
            vo.setName("3.出国");
            vo.setpId("9");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("94");
            vo.setName("4.代表大会");
            vo.setpId("9");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);

            vo = new B01TreeVo();
            vo.setId("10");
            vo.setName("10.其他供参考材料");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(false);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            // 添加未分组节点
            map.put("customTree", b01TreeVoList);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e,e);
            map.put("success", false);
            map.put("message", "服务器忙，请稍后再试！");
        }
        return map;
    }
    @RequestMapping(value = "/ajax/mlxxList")
    public ModelAndView mlxxList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/manage/mlxxList",map);
    }

    @RequestMapping(value = "/ajax/viewA01")
    public ModelAndView viewA01(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/viewA01");
    }
    @RequestMapping(value = "/ajax/zhuanchu")
    public ModelAndView zhuanzhu(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/zhuanchu");
    }

    @RequestMapping(value = "/ajax/down")
    public void fileDown(String type, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String filePath = "";
        if(type!=null && type.equals("dianzibiaogemulu")){
            filePath = File.separator+"da"+File.separator+"叶红专的电子档案(20180402).xls";
        }else if(type!=null && type.equals("allDa")) {
            filePath = File.separator+"da" + File.separator + "叶红专的档案（含图片).rar";
        }else if(type!=null && type.equals("qianquecail")) {
            filePath = File.separator+"da" + File.separator + "干部档案个人欠缺材料情况表.xls";
        }else if(type!=null && type.equals("dangantupianxiazai")) {
            filePath = File.separator+"da" + File.separator + "0002.rar";
        }else if(type!=null && type.equals("xiazaimludaorumoban")) {
            filePath = File.separator+"da" + File.separator + "干部档案目录模板.xls";
        }else if(type!=null && type.equals("daochumilu")) {
            filePath = File.separator+"da" + File.separator + "干部档案目录.xls";
        }
        resp.setContentType("multipart/form-data");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        resp.setHeader("Content-Disposition", "attachment;fileName=" + encode(filePath.substring(filePath.lastIndexOf(File.separator) + 1)));
        OutputStream output = resp.getOutputStream();
        byte[] b = FileUtils.readFileToByteArray(new File(uploadAbsolutePath + filePath));
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
    @RequestMapping(value = "/ajax/addMlcl")
    public ModelAndView addMlcl(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/addMlcl");
    }
    @RequestMapping(value = "/ajax/editMlcl")
    public ModelAndView editMlcl(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/editMlcl");
    }
    @RequestMapping(value = "/ajax/uploadImg")
    public ModelAndView uploadImg(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/uploadImg");
    }

    @RequestMapping(value = "/ajax/viewImgManage")
    public ModelAndView viewImgManage(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/viewImgManage");
    }

    @RequestMapping(value = "/ajax/viewImg")
    public ModelAndView viewImg(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/viewImg");
    }

    @RequestMapping(value = "/ajax/zjclList")
    public ModelAndView zjclList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/manage/zjclList",map);
    }
    @RequestMapping(value = "/ajax/editZjcl")
    public ModelAndView editZjcl(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/editZjcl");
    }
    @RequestMapping(value = "/ajax/addZjcl")
    public ModelAndView addZjcl(){
        return new ModelAndView("saas/zzb/app/console/a38/manage/addZjcl");
    }

    @RequestMapping(value = "/shList")
    public ModelAndView shList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/sh/shList",map);
    }
    @RequestMapping(value = "/shBase")
    public ModelAndView shBase(String loadType){
        Map<String, Object> map = Maps.newHashMap();
        map.put("loadType",loadType);
        return new ModelAndView("saas/zzb/app/console/a38/sh/base",map);
    }
    @RequestMapping(value = "/zrManageList")
    public ModelAndView zrManageList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 1, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/zrzc/zrManageList",map);
    }
    @RequestMapping(value = "/zrBase")
    public ModelAndView zrBase(String loadType){
        Map<String, Object> map = Maps.newHashMap();
        map.put("loadType",loadType);
        return new ModelAndView("saas/zzb/app/console/a38/zrzc/base",map);
    }
    @RequestMapping(value = "/zcManageList")
    public ModelAndView zcManageList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/zrzc/zcManageList",map);
    }
    @RequestMapping(value = "/zrRecodeList")
    public ModelAndView zrRecodeList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/zrzc/zrRecodeList",map);
    }
    @RequestMapping(value = "/zcRecodeList")
    public ModelAndView zcRecodeList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/zrzc/zcRecodeList",map);
    }
    @RequestMapping(value = "/cyApplyList")
    public ModelAndView cyApplyList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/cyApplyList",map);
    }
    @RequestMapping(value = "/ajax/addApplyDa")
    public ModelAndView addApplyDa(){
        return new ModelAndView("saas/zzb/app/console/a38/cygl/addApplyDa");
    }
    @RequestMapping(value = "/cyManageList")
    public ModelAndView cyManageList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/cyManageList",map);
    }
    @RequestMapping(value = "/shouquan")
    public ModelAndView shouquan(){
        return new ModelAndView("saas/zzb/app/console/a38/cygl/shouquan");
    }
    @RequestMapping(value = "/ajax/chayueshichang")
    public ModelAndView chayueshichang(){
        return new ModelAndView("saas/zzb/app/console/a38/cygl/chayueshichang");
    }
    @RequestMapping(value = "/ajax/chayuexiangxi")
    public ModelAndView chayuexiangxi(){
        return new ModelAndView("saas/zzb/app/console/a38/cygl/chayuexiangxi");
    }
    @RequestMapping(value = "/cydaList")
    public ModelAndView cydaList(){
        return new ModelAndView("saas/zzb/app/console/a38/cygl/cydaList");
    }
    @RequestMapping(value = "/ajax/cysqdaList")
    public ModelAndView cysqdaList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 1, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/cysqdaList",map);
    }
    @RequestMapping(value = "/ajax/cyshouquandaList")
    public ModelAndView cyshouquandaList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/cyshouquandaList",map);
    }
    @RequestMapping(value = "/cyRecodeList")
    public ModelAndView cyRecodeList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/cyRecodeList",map);
    }

    @RequestMapping(value = "/ajax/zzydRecodeList")
    public ModelAndView zzydRecodeList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 1, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/zzydRecodeList",map);
    }
    @RequestMapping(value = "/ajax/sqydRecodeList")
    public ModelAndView sqydRecodeList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/sqydRecodeList",map);
    }
    @RequestMapping(value = "/ajax/shouquanydRecodeList")
    public ModelAndView shouquanydRecodeList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/shouquanydRecodeList",map);
    }

    @RequestMapping(value = "/ajax/viewTime")
    public ModelAndView viewTime(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/viewTime",map);
    }
    @RequestMapping(value = "/ajax/viewNeiRong")
    public ModelAndView viewNeiRong(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/cygl/viewNeiRong",map);
    }

    @RequestMapping(value = "/catalogTypeManage")
    public ModelAndView catalogTypeManage(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/mlgl/catalogTypeManage",map);
    }
    @RequestMapping(value = "/ajax/catalogTypeManageList")
    public ModelAndView catalogTypeManageList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/mlgl/catalogTypeManageList",map);
    }
    @RequestMapping(value = "/ajax/catalogTypeAdd")
    public ModelAndView catalogTypeAdd(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/a38/mlgl/catalogTypeAdd",map);
    }

}
