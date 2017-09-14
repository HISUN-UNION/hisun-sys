package com.hisun.saas.sys.admin.user.controller;

import com.google.common.collect.Maps;
import com.hisun.saas.sys.admin.user.entity.User;
import com.hisun.base.auth.Constants;
import com.hisun.base.auth.KaptchaUsernamePasswordToken;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.admin.log.entity.Log;
import com.hisun.saas.sys.admin.log.service.LogService;
import com.hisun.saas.sys.admin.message.entity.Notice;
import com.hisun.saas.sys.admin.message.service.NoticeService;
import com.hisun.saas.sys.admin.resource.service.ResourceService;
import com.hisun.saas.sys.admin.user.service.UserService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.util.AddressUtil;
import com.hisun.util.WrapWebUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.owasp.csrfguard.CsrfGuard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {

	@Resource
	private ResourceService resourceService;
	@Resource
	private UserService userService;
	
	@Resource
	private LogService logService;

	@Resource
	private NoticeService noticeService;
	
	@Value("${elasticsearch.ip}")
	private String elasticsearchUrl;
	@Value("${elasticsearch.rest.port}")
	private String elasticsearchPort;

	@Value("${login.admin.title}")
	private String loginTitle;
	
	@Value("${login.logo}")
	private String loginLogo;
	
	//@Value(value="${domain}")
	//private String resourceDomain;
	@Value(value="${monitor.restful.url}")
	private String monitorUrl;
	
	@Value("${mainModule}")
	private String mainModule;

	@Value(value="${captcha.activated}")
	private boolean captchaActivated;

	@Value(value = "${communication.sms.on}")
	private boolean smsOn;

	@RequestMapping(value = "/signin")
	public String signin(User loginUser, Model model, boolean remember, String code, HttpServletRequest req) {
		Subject currentUser = SecurityUtils.getSubject();
		KaptchaUsernamePasswordToken token = new KaptchaUsernamePasswordToken(
				loginUser.getUsername(), loginUser.getPassword(),false,code,true);
		//if(remember){
			token.setRememberMe(false);
		//}
		try {
			currentUser.login(token);
			UserLoginDetails userLoginDetails = userService.findUserLoginDetails(loginUser.getUsername());
			User user = userService.getByPK(userLoginDetails.getUserid());
			if(user!=null && user.getLocked()){
				model.addAttribute("error","3");
				model.addAttribute("username", loginUser.getUsername());
				return "redirect:/admin/login";
			}else{
				currentUser.getSession().setAttribute(Constants.CURRENT_USER, userLoginDetails);

				Log log = new Log();
				String ip = WrapWebUtils.getRemoteIp();
				try {
					if(smsOn) {
						ip = AddressUtil.getIpInformation(ip);
					}
				} catch (Exception e1) {
					logger.error(e1.getMessage());
				} finally{
					log.setIp(ip);
					//log.setContent(content);
					log.setUserId(userLoginDetails.getUserid());
					log.setCreateTime(new Date());
					log.setType(Short.valueOf("4"));
					logService.log(log);
				}
				CsrfGuard csrfGuard = CsrfGuard.getInstance();
				csrfGuard.updateToken(WrapWebUtils.getSession());
				if(user.getAdmin()){
					return "redirect:/sys/admin/user/list?OWASP_CSRFTOKEN="+ WrapWebUtils.getSession().getAttribute("OWASP_CSRFTOKEN");
				}else{
					return "redirect:/sys/admin/user/profile?OWASP_CSRFTOKEN="+ WrapWebUtils.getSession().getAttribute("OWASP_CSRFTOKEN");
				}

			}

		} catch (AuthenticationException e) {
			token.clear();
			if(e.getCause() instanceof GenericException){
				logger.error("验证码错误!");
				model.addAttribute("error", "2");
			} else if(e instanceof ExcessiveAttemptsException){
				logger.error("当天错误输入5次密码，该账号已被锁定!");
				model.addAttribute("error","3");
			} else{
				logger.error(e,e);
				model.addAttribute("error", "1");
			}
			model.addAttribute("username", loginUser.getUsername());
			
			return "redirect:/admin/login";
		}
		// List<Resource> menus = resourceService.findMenus(permissions);
		// model.addAttribute("menus", menus);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest req, Model model) {
		//smsService.getTpl(771065L);
		UserLoginDetails userLoginDetails = (UserLoginDetails) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
		if(SecurityUtils.getSubject().isAuthenticated()&&userLoginDetails!=null){
			// return new ModelAndView("redirect:/admin/dashboard");
			return new ModelAndView("redirect:/sys/admin/user/list");
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("loginTitle", loginTitle);
		map.put("loginLogo", loginLogo);
		map.put("captchaActivated", captchaActivated);
		map.put("smsOn", smsOn);
		return new ModelAndView("saas/sys/admin/login",map);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)  
    public String logout() {  
        Subject currentUser = SecurityUtils.getSubject();  
        UserLoginDetails userLoginDetails = (UserLoginDetails) currentUser.getSession().getAttribute(Constants.CURRENT_USER);
        User user = new User();
		user.setId(userLoginDetails.getUserid());
        currentUser.logout();
        return "redirect:/admin/login";
    }
	
	@RequestMapping(value = "/adminDashboard")
	public ModelAndView adminDashboard() {
		return  new ModelAndView("saas/sys/adminDashboard");
	}
	
	@RequestMapping(value = "/forgot")
	public ModelAndView forgetPassword() {
		return  new ModelAndView("saas/sys/admin/user/forgot");
	}

	@RequiresPermissions("dashboard:*")
	@RequestMapping(value = "/dashboard")
	public ModelAndView dashboard() {
		ModelAndView modelAndView = null;
		UserLoginDetails userLoginDetails = (UserLoginDetails) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER);
		if ("DataAnalyse".equals(mainModule)) {//数据分析模块独立部署时，登录成功后首页的跳转控制
			modelAndView = new ModelAndView("redirect:/admin/logOverview");
		}else if(StringUtils.equals(Constants.ADMIN_USERNAME, userLoginDetails.getUsername())){
			modelAndView = new ModelAndView("redirect:/sys/admin/user/list");
		}else{
			Map<String, Object> map = Maps.newHashMap();
			CommonConditionQuery query = new CommonConditionQuery();
			query.add(CommonRestrictions.and(" pushWay = :pushWay ", "pushWay", Short.valueOf("2")));
			query.add(CommonRestrictions.and(" status = :status ", "status", Boolean.TRUE));
			CommonOrderBy orderBy = new CommonOrderBy();
			List<Notice> resultList = noticeService.list(query, orderBy, 1, 20);
			map.put("noticeContents", resultList);
			modelAndView = new ModelAndView("saas/sys/admin/dashboard",map);
		}
		return modelAndView;
	}
	

}

