package org.kay.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_ROLE_PRIVILEGE")
public class SysRolePrivilege implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String rolePrivilegeId;
	private SysRole sysRole;
	private SysPrivilege sysPrivilege;

	public SysRolePrivilege() {
	}

	@Id
	@Column(name = "ROLE_PRIVILEGE_ID", unique = true, nullable = false, length = 32)
	public String getRolePrivilegeId() {
		return this.rolePrivilegeId;
	}

	public void setRolePrivilegeId(String rolePrivilegeId) {
		this.rolePrivilegeId = rolePrivilegeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRIVILEGE_ID", nullable = false)
	public SysPrivilege getSysPrivilege() {
		return this.sysPrivilege;
	}

	public void setSysPrivilege(SysPrivilege sysPrivilege) {
		this.sysPrivilege = sysPrivilege;
	}

}