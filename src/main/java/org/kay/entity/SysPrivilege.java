package org.kay.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_PRIVILEGE")
public class SysPrivilege implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String privilegeId;
	private SysResource sysResource;
	private SysOperation sysOperation;
	private Set<SysRolePrivilege> sysRolePrivileges = new HashSet<SysRolePrivilege>(0);

	public SysPrivilege() {
	}

	@Id
	@Column(name = "PRIVILEGE_ID", unique = true, nullable = false, length = 32)
	public String getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCE_ID")
	public SysResource getSysResource() {
		return this.sysResource;
	}

	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATION_ID")
	public SysOperation getSysOperation() {
		return this.sysOperation;
	}

	public void setSysOperation(SysOperation sysOperation) {
		this.sysOperation = sysOperation;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysPrivilege")
	public Set<SysRolePrivilege> getSysRolePrivileges() {
		return this.sysRolePrivileges;
	}

	public void setSysRolePrivileges(Set<SysRolePrivilege> sysRolePrivileges) {
		this.sysRolePrivileges = sysRolePrivileges;
	}

}