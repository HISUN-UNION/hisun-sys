package com.hisun.saas.sys.admin.message.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.admin.message.dao.NoticeDao;
import com.hisun.saas.sys.admin.message.entity.Notice;
import com.hisun.saas.sys.admin.message.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: LogServiceImpl.java </p>
 * <p>Package com.hisun.cloud.sys.service.impl </p>
 * <p>Description: TODO</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 湖南海数互联信息技术有限公司</p>
 * @author Jason
 * @email jason4j@qq.com
 * @date 2015年5月21日 上午11:32:59 
 * @version 
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice, String> implements NoticeService {

	private NoticeDao noticeDao;
	
	@Override
	@Autowired
	public void setBaseDao(BaseDao<Notice, String> noticeDao) {
		this.baseDao = noticeDao;
		this.noticeDao = (NoticeDao) noticeDao;
		
	}
}
