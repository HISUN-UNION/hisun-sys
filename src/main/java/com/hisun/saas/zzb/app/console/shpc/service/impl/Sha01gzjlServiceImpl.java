package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01gzjlDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gzjl;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gzjlService;
import com.hisun.util.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01gzjlServiceImpl extends BaseServiceImpl<Sha01gzjl,String> implements Sha01gzjlService {

    private Sha01gzjlDao sha01gzjlDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01gzjl, String> sha01gzjlDao) {
        this.baseDao = sha01gzjlDao;
        this.sha01gzjlDao = (Sha01gzjlDao) sha01gzjlDao;
    }



    public void saveFromWord(Sha01 sha01, Map<String, String> dataMap){
        Sha01gzjl gzjl = null;
        String gzjlStr = null;
        Map<String,String> listDataMap = new HashMap<String,String>();
        for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            //组合主表以"list"
            if(key.startsWith(WordUtil.listSign) && key.indexOf("APP_SH_A01_GZJL")>0) {
                gzjlStr= dataMap.get(key);
            }
        }
        //解析gzjlStr
        if(gzjlStr!=null){
            int px =1;
           List<String> list = matcherGzjl(gzjlStr);
            for(String str : list){
                gzjl = new Sha01gzjl();
                gzjl.setSha01(sha01);
                gzjl.setTenant(sha01.getTenant());
                gzjl.setJlsm(str);
                gzjl.setPx(px);
                px++;
                this.sha01gzjlDao.save(gzjl);
            }
        }

    }

    private List<String> matcherGzjl(String str){
        List<String> list = new ArrayList<String>();
        //工作经历正则
        String parttern = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}).(?:0?[1-9]|1[0-2])"
                +"-(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}).(?:0?[1-9]|1[0-2]))?"
                +"\\s*\\S\\W*([^\\(\\（]*[\\)\\）])?";
        Pattern r = Pattern.compile(parttern);
        Matcher matcher = r.matcher(str);
        while (matcher.find()){
            System.out.println("=="+matcher.group(0).trim());
            list.add(matcher.group(0));
        }
        return list;
    }

    public static void main (String[] args ){
        String str ="1984.07-1991.05 浙江省平阳县物价局、县委办公室、县委组织部、党史研究\n" +
                "                室工作，历任县委办秘书、团支书（其间：1988.09--1990.1\n" +
                "                2  杭州大学函授部中文系中文专业学习，获文学学士学位）\n" +
                "1991.05-1995.05 广东省人民检察院政治部科员、科室负责人、副科长、科长\n" +
                "                （其间：1992.02-1992.05 借调最高人民检察院政治部工作，\n" +
                "                1992.09-1995.07  广东广播电视大学法律专业学习）\n" +
                "1995.05-2000.02 广东省委组织部地方干部处主任科员（其间：1996.09-1999\n" +
                "                .08 中山大学研究生院政治经济学专业学习，获经济学硕士学\n" +
                "                位；1999.06--2000.01 带队驻韶关市翁源县阳东村抓农村基\n" +
                "                层组织建设）\n" +
                "2000.02-2000.03 广东省委组织部地方干部处助理调研员\n" +
                "2000.03-2003.03 增城市委常委、组织部部长（其间：1999.09-2002.05  暨南\n" +
                "                大学管理学院企业管理系产业经济学专业学习，获经济学博士\n" +
                "                学位）\n" +
                "2003.03-2006.09 增城市委常委、副市长（其间：2004.04--2006.04 南开大学\n" +
                "                经济学院人口、资源与环境经济学专业博士后）\n" +
                "2006.09-2011.04 广州市旅游局副局长、党委委员\n" +
                "2011.04-2011.05 广州市城市管理委员会党委副书记，广州市旅游局副局长、\n" +
                "                党委委员\n" +
                "2011.05-2013.01 广州市城市管理委员会党委副书记\n" +
                "2013.01-        广州市民政局党委书记";
        String parttern = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}).(?:0?[1-9]|1[0-2])-(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}).(?:0?[1-9]|1[0-2]))?\\s*\\S\\W*([^\\(\\（]*[\\)\\）])?";

        Pattern r = Pattern.compile(parttern);
        Matcher matcher = r.matcher(str);
        int i = 0;
        while (matcher.find()){
            System.out.println(matcher.group(0).replaceAll("\\vt",""));
        }


    }

}