package com.hisun.saas.sys.admin.user.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hisun.saas.sys.admin.resource.entity.Resource;
import com.hisun.saas.sys.admin.user.dao.UserDao;
import com.hisun.saas.sys.admin.user.entity.User;
import com.hisun.saas.sys.admin.user.entity.UserRole;
import com.hisun.saas.sys.admin.user.service.UserService;
import com.hisun.saas.sys.admin.user.vo.UserVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.base.auth.Constants;
import com.hisun.base.auth.service.PasswordHelper;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.entity.AbstractRole;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.base.vo.PagerVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * 
 * <p>
 * 类名称：UserServiceImpl
 * </p>
 * <p>
 * 类描述:
 * </p>
 * <p>
 * 公司：湖南海数互联信息技术有限公司
 * </p>
 *
 * @创建人：qinjw
 * @创建时间：2015年3月18日 下午3:54:32
 * @创建人联系方式：qinjw@30wish.net
 * @version
 */

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements
		UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
			
	private UserDao userDao;

	@Autowired
	private PasswordHelper passwordHelper;
	
	@Override
	public String save(User entity) {
		 //加密密码
        passwordHelper.encryptPassword(entity);
        //Tenant tenant = new Tenant();
        //tenant.setId(UserLoginDetailsUtil.getUserLoginDetails().getTenantId());
        //entity.setTenant(tenant);
        String pk = super.save(entity);
        //this.baseDao.flush();
		return pk;
	}

	@Override
	public void update(User entity, boolean changePassword) {
		if(changePassword){
			//加密密码
			passwordHelper.encryptPassword(entity);
		}
		super.update(entity);
		//this.baseDao.flush();
	}

	@Override
	public void update(User entity) {
		super.update(entity);
		//this.baseDao.flush();
	}


	@Autowired
	public void setBaseDao(BaseDao<User, String> userDao) {
		this.baseDao = userDao;
		this.userDao = (UserDao) userDao;
	}

	@Override
	public User findByUsername(String username) {
		CommonConditionQuery query = new CommonConditionQuery();
		query.add(CommonRestrictions.and(" User.username = :username",
				"username", username));
		List<User> userList = userDao.list(query, null);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}


	@Override
	public UserLoginDetails findUserLoginDetails(String username) {
		
		User user = this.findByUsername(username);
		if (user != null) {
			UserLoginDetails userLoginDetails = new UserLoginDetails();
			//BeanMapper.copy(user, userLoginDetails);
			try {
				BeanUtils.copyProperties(userLoginDetails, user);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error(e,e);
			}
			userLoginDetails.setUserid(user.getId());
			userLoginDetails.setUser(user);
			userLoginDetails.setType(Constants.TYPE_PLATF);
			//List<Role> roles = new ArrayList<Role>();
			//roles.add(user.getRole());
			List<AbstractRole> roles = Lists.newArrayList();
			List<UserRole> userRoles = user.getUserRoles();
			List<Resource> resources = Lists.newArrayList();
			for(UserRole userRole : userRoles){
				roles.add(userRole.getRole());
				resources.addAll(userRole.getRole().getResources());
			}
			userLoginDetails.setRoles(roles);
			
			Set<Resource> set = Sets.newLinkedHashSet();
			set.addAll(resources);
			List<Resource> resources2 = new ArrayList<Resource>();
			for(Resource resource : set){
				if(resource.getStatus()==0){
					resources2.add(resource);
				}
			}
			Collections.sort(resources2);
			userLoginDetails.setResources(resources2);
			return userLoginDetails;
		}
		return null;
	}

	@Override
	public boolean credentialsPassword(User user, String inputPassword) {
		return passwordHelper.credentialsPassword(user, inputPassword);
	}

	@Override
	public User findByEmail(String email) {
		CommonConditionQuery query = new CommonConditionQuery();
		query.add(CommonRestrictions.and(" User.email = :email",
				"email", email));
		List<User> userList = userDao.list(query, null);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public PagerVo<UserVo> searchUserByName(int pageSize, int pageNum,
											UserVo userVo) throws Exception {
		Session session = userDao.getSession();
		PagerVo<UserVo> pager = null;
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuilder countSql = new StringBuilder();
		int count;
		StringBuilder sql = new StringBuilder();
		map.put("name", "%" + userVo.getUsername() + "%");

		countSql.append(" select count(1) from SYS_USER t");
		countSql.append(" where ");
		countSql.append("  t.user_name <>'admin' ");
		countSql.append(" and ( t.user_name like :name");
		countSql.append(" or t.real_name like :name");
		countSql.append(" or t.tel like :name");
		countSql.append(" or t.email like :name )");
		sql.append(" select t.id,t.email,t.real_name,t.tel,t.user_name,t.locked from SYS_USER t ");
		sql.append(" where ");
		sql.append("  t.user_name <>'admin' ");
		sql.append(" and ( t.user_name like :name ");
		sql.append(" or t.real_name like :name");
		sql.append(" or t.tel like :name");
		sql.append(" or t.email like :name )");
		Query query = session.createSQLQuery(countSql.toString());
		query.setProperties(map);
		count = Integer.parseInt(query.uniqueResult().toString());
		
		query = session.createSQLQuery(sql.toString());
		query.setProperties(map);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNum-1)*pageSize);
		List<Object[]> objects=query.list();
		
		List<UserVo> list = new ArrayList<UserVo>();
		UserVo userVos = null;
		if(objects!=null&&objects.size()>0)
		{
			for(Object[] obs:objects)
			{
				int index=0;
				userVos = new UserVo();
				userVos.setId((String)obs[index++]);
				userVos.setEmail((String)obs[index++]);
				userVos.setRealname((String)obs[index++]);
				userVos.setTel((String)obs[index++]);
				userVos.setUsername((String)obs[index++]);
				userVos.setLocked((Boolean) obs[index++]);
				list.add(userVos);
			}
		}
		
		pager = new PagerVo<UserVo>(list,count,pageNum,pageSize);
		return pager;
	}

}
