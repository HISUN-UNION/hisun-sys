package com.hisun.saas.sys.auth;

import com.hisun.base.auth.Constants;
import com.hisun.util.WrapWebUtils;
import org.apache.shiro.SecurityUtils;
/**
 * jstl自定义function方法
* @ClassName: Function 
* @Description: 
* @author lyk
* @date 2015年3月19日
 */
public class UserLoginDetailsUtil {

	public static UserLoginDetails getUserLoginDetails(){
		UserLoginDetails detail = null;
		if(WrapWebUtils.getRequest() != null){
			//从request获取是因为成都接口
			detail = (UserLoginDetails) WrapWebUtils.getRequest().getAttribute(Constants.CURRENT_USER);
		}
		if(detail==null){
			detail = (UserLoginDetails)SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
		}
		return detail;
	}
}
