package org.kay.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kay.entity.SysUser;
import org.kay.framework.persistence.dao.BaseDao;
import org.kay.framework.persistence.model.QueryParamList;
import org.kay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void addUser(SysUser user) {
		this.baseDao.persist(user);
	}

//	@Override
//	public List<Suser> queryUser(String userId) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("userId", userId);
//		String qlString = " from Suser s where s.userId = :userId ";
//		return this.baseDao.find(qlString, params);
//	}

	@Override
	public SysUser select(String username) {
		QueryParamList paramList = new QueryParamList();
		paramList.addParam("username", username);
		String qlString = " from SysUser s where s.userCode = :username ";
		List<SysUser> sysUsers = this.baseDao.find(qlString, paramList, null, null);
		if (sysUsers.size() > 0) {
			return sysUsers.get(0);
		} else {
			return null;
		}
		
	}

	@Override
	public void update(SysUser s) {
		this.baseDao.merge(s);
	}

	@Override
	public SysUser loadById(Class<SysUser> entityClass, Serializable primaryKey) {
		return this.baseDao.find(entityClass, primaryKey);
	}

}
