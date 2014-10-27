package org.kay.framework.security;

import org.apache.log4j.Logger;
import java.util.List;
import org.kay.entity.SysUser;
import org.kay.framework.persistence.dao.BaseDao;
import org.kay.framework.persistence.model.QueryParamList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

	private static final Logger logger = Logger.getLogger(UserDetailServiceImpl.class);
	
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		SysUser sysUser = null;
		try {
			QueryParamList paramList = new QueryParamList();
			paramList.addParam("userCode", username);
			String queryJpql = "select s from SysUser s where s.userCode = :userCode ";
			List<SysUser> sysUsers = baseDao.find(queryJpql, paramList);
			
			if (sysUsers.size() > 0) {
				sysUser = sysUsers.get(0);
			} else {
				throw new UsernameNotFoundException("Username not found!");
			}
		} catch (Exception e) {
			logger.error(" Load user by username error", e);
		}
		return sysUser;
	}

}
