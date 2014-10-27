package org.kay.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_ROLE")
public class SysRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleCode;
	private String roleName;
	private String enabled;
	private Set<SysRoleUser> sysRoleUsers = new HashSet<SysRoleUser>(0);
	private Set<SysRolePrivilege> sysRolePrivileges = new HashSet<SysRolePrivilege>(0);

	public SysRole() {
	}

	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false, length = 32)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_CODE", length = 32)
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "ROLE_NAME", length = 64)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ENABLED", length = 1)
	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysRoleUser> getSysRoleUsers() {
		return this.sysRoleUsers;
	}

	public void setSysRoleUsers(Set<SysRoleUser> sysRoleUsers) {
		this.sysRoleUsers = sysRoleUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysRolePrivilege> getSysRolePrivileges() {
		return this.sysRolePrivileges;
	}

	public void setSysRolePrivileges(Set<SysRolePrivilege> sysRolePrivileges) {
		this.sysRolePrivileges = sysRolePrivileges;
	}

}