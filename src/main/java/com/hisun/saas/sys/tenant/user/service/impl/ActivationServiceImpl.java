package com.hisun.saas.sys.tenant.user.service.impl;

import com.hisun.saas.sys.admin.communication.service.MailService;
import com.hisun.saas.sys.admin.communication.vo.MailSendSingleVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.tenant.role.dao.TenantRoleDao;
import com.hisun.saas.sys.tenant.role.entity.TenantRole;
import com.hisun.saas.sys.tenant.user.dao.ActivationDao;
import com.hisun.saas.sys.tenant.user.entity.Activation;
import com.hisun.saas.sys.tenant.user.service.ActivationService;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *<p>类名称：ActivationServiceImpl</p>
 *<p>类描述: </p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：qinjw
 *@创建时间：2015年3月18日 下午5:09:03
 *@创建人联系方式：qinjw@30wish.net
 *@version
 */

@Service
public class ActivationServiceImpl extends
        BaseServiceImpl<Activation, String> implements ActivationService {

	private ActivationDao activationDao;

    @Autowired
    private MailService mailService;

    @Autowired
    private TenantRoleDao tenantRoleDao;

    @Value(value="${domain}")
    public String domain;

    @Autowired
	public void setBaseDao(BaseDao<Activation, String> activationDao) {
		this.baseDao = activationDao;
        this.activationDao = (ActivationDao) activationDao;
	}

    @Override
    public void saveAndSendEmail(String email, String emailContent, String roleId) throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        String realName = userLoginDetails.getRealname();
        String orgName = userLoginDetails.getTenantName();
        String userId = userLoginDetails.getUserid();
        String userName = userLoginDetails.getUsername();

        Activation activation = new Activation();
        activation.setEmail(email);
        activation.setStatus("0");
        activation.setInviteUserId(userId);
        activation.setInviteUserName(userName);
        activation.setInviteTenantId(userLoginDetails.getTenantId());
        // roleId为空,则设置普通用户角色
        if (StringUtils.isNotBlank(roleId)) {
            TenantRole role = tenantRoleDao.getByCode("ROLE_TENANT");
            activation.setRoleId(role.getId());
        } else {
            activation.setRoleId(roleId);
        }

        StringBuffer contentBuffer = new StringBuffer();
        String pk = this.activationDao.save(activation);
        contentBuffer.append(domain + "/sys/tenant/user/activate");
        contentBuffer.append("?activationId=" + pk);

        String content = "您好， " + orgName + " 的 " + realName + " 邀请你注册成为【三零优异服务】的用户，请点击如下地址进行激活注册。\n" + contentBuffer.toString();
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("orgName",orgName);
        paramMap.put("realName",StringUtils.isBlank(realName)?userLoginDetails.getUsername():realName);
        paramMap.put("url",contentBuffer.toString());
        MailSendSingleVo mailSendVo = new MailSendSingleVo(email,paramMap);
        mailService.sendEmail("inviteregister", null, null, mailSendVo, Boolean.TRUE);
        //TODO 邮件模板
        //this.mailService.sendEmail("【三零优异服务网】邀请注册", StringUtils.trim(emailContent)+content,activation.getEmail());//发送邀请邮件

    }
}
