package com.hisun.saas.zzb.app.console.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouying on 2017/12/3.
 */
public class GzjlUtil {

    public static List<String> matchGzjlStr(String gzjlStr){
        List<String> list = new ArrayList<String>();
        //工作经历正则
        String parttern = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}).(?:0?[1-9]|1[0-2])"
                +"--(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}).(?:0?[1-9]|1[0-2]))?"
                +"\\s*\\S\\W*([^\\(\\（]*[\\)\\）])?";
        Pattern r = Pattern.compile(parttern);
        Matcher matcher = r.matcher(gzjlStr);
        while (matcher.find()){
            list.add(matcher.group(0));
        }
        return list;
    }
}
