package org.kay.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_ROLE_USER")
public class SysRoleUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userRoleId;
	private SysUser sysUser;
	private SysRole sysRole;

	public SysRoleUser() {
	}

	@Id
	@Column(name = "USER_ROLE_ID", unique = true, nullable = false, length = 32)
	public String getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}