package org.kay.service;

import java.io.Serializable;

import org.kay.entity.SysUser;

public interface UserService {
	SysUser select(String username);

	void addUser(SysUser s);

	void update(SysUser s);

	SysUser loadById(Class<SysUser> clazz, Serializable id);
}
