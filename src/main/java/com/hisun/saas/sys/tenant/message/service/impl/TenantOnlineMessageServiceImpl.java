package com.hisun.saas.sys.tenant.message.service.impl;

import com.google.common.collect.Maps;
import com.hisun.saas.sys.tenant.message.dao.TenantOnlineMessageDao;
import com.hisun.saas.sys.tenant.message.entity.TenantOnlineMessage;
import com.hisun.saas.sys.tenant.message.service.TenantOnlineMessageService;
import com.hisun.saas.sys.tenant.message.vo.TenantOnlineMessageData;
import com.hisun.saas.sys.tenant.tenant.dao.TenantDao;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.sys.tenant.user.dao.TenantUserDao;
import com.hisun.saas.sys.tenant.user.entity.TenantUser;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * <p>类名称：TenantOnlineMessageServiceImpl</p>
 * <p>类描述：</p>
 * <p>公司：湖南海数互联信息技术有限公司</p>
 *
 * @创建人：lihaiming
 * @创建时间：15/11/20 下午4:19
 * @创建人联系方式：lihm_gz@30wish.net
 */
@Service
public class TenantOnlineMessageServiceImpl extends BaseServiceImpl<TenantOnlineMessage, String> implements TenantOnlineMessageService {

    private TenantOnlineMessageDao tenantOnlineMessageDao;

    @Autowired
    private TenantUserDao tenantUserDao;

    @Autowired
    private TenantDao tenantDao;

    @Override
    @Autowired
    public void setBaseDao(BaseDao<TenantOnlineMessage, String> baseDao) {
        this.baseDao = baseDao;
        this.tenantOnlineMessageDao = (TenantOnlineMessageDao) baseDao;
    }

    @Override
    public void saveOnlineMessage(TenantOnlineMessageData onlineMessageData) {
        //UserLoginDetails userLoginDetails = Function.getUserLoginDetails();

        TenantUser createUser = this.tenantUserDao.getByPK(onlineMessageData.getUserId());
        Tenant tenant = this.tenantDao.getByPK(onlineMessageData.getTenantId());
        TenantOnlineMessage onlineMessage = new TenantOnlineMessage();

        onlineMessage.setCreateDate(new Date());
        onlineMessage.setCreateUserName(createUser.getRealname());
        onlineMessage.setTenant(tenant);
        onlineMessage.setType(onlineMessageData.getType().getValue());
        onlineMessage.setContent(onlineMessageData.getContent());
        onlineMessage.setTitle(onlineMessageData.getTitle());
        onlineMessage.setCreateUserId(onlineMessageData.getUserId());
        this.tenantOnlineMessageDao.save(onlineMessage);
    }

    @Override
    public int update(String sql, Map<String, Object> paramMap) {
        return this.tenantOnlineMessageDao.update(sql, paramMap);
    }

    @Override
    public void updateAllRead(String createUserId) {
        StringBuilder sb = new StringBuilder("update SYS_TENANT_ONLINE_MESSAGE set status=:status where create_user_id=:id");
        Map<String, Object> columns = Maps.newHashMap();
        columns.put("status", Short.valueOf("2"));
        columns.put("id", createUserId);
        this.tenantOnlineMessageDao.update(sb.toString(), columns);
    }
}
