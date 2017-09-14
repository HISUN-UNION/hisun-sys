package com.hisun.saas.sys.admin;

/**
 * <p>Title: Constants.java</p>
 * <p>Description: 平台常量定义</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 湖南海数互联信息技术有限公司</p>
 *
 * @author Jason
 * @version v0.1
 * @email jason4j@qq.com
 * @date 2015-11-24 10:31
 */
public class Constants {

    //资源类型菜单
    public static final Integer RESOURCE_APP=Integer.valueOf(2);//资源系统
    public static final Integer RESOURCE_MENU=Integer.valueOf(0);//资源菜单
    public static final Integer RESOURCE_ACTION=Integer.valueOf(1);//资源操作


    //状态定义
    public static final Integer AVAILABLE_STATUS = Integer.valueOf(0);//可用
    public static final Integer NOT_AVAILABLE_STATUS = Integer.valueOf(1);//不可用


    //忘记密码  邮件发送的短信code
    public static final String SMS_SESSION_KEY = "SMS_SESSION_KEY";

}
