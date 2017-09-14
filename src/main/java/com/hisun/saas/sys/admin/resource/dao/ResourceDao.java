package com.hisun.saas.sys.admin.resource.dao;

import com.hisun.saas.sys.admin.resource.entity.Resource;
import com.hisun.base.dao.BaseDao;

import java.util.List;

/**
 *<p>类名称：PlatfResourceDao</p>
 *<p>类描述: </p>
 *<p>公司：湖南海数互联信息技术有限公司</p>
 *@创建人：Jason
 *@创建时间：2015-3-18 15:43:37
 *@创建人联系方式：jason4j@qq.com
 *@version
 */
public interface ResourceDao extends BaseDao<Resource, String> {

	/**
	 * 根据父节点id和资源类型获取子节点最大的排序
	 *
	 * @param pId
	 * @param type
	 * @return
	 */
	Integer getMaxSort(String pId, int type);

	/**
	 * 根据租户用户ID获取资源
	 * @param tenantUserId
	 * @return
	 */
	List<Resource> getByTenantUserId(String tenantUserId);
}
