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
@Table(name = "SYS_OPERATION")
public class SysOperation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String operationId;
	private String operationName;
	private Set<SysPrivilege> sysPrivileges = new HashSet<SysPrivilege>(0);

	public SysOperation() {
	}

	@Id
	@Column(name = "OPERATION_ID", unique = true, nullable = false, length = 32)
	public String getOperationId() {
		return this.operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	@Column(name = "OPERATION_NAME", length = 64)
	public String getOperationName() {
		return this.operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysOperation")
	public Set<SysPrivilege> getSysPrivileges() {
		return this.sysPrivileges;
	}

	public void setSysPrivileges(Set<SysPrivilege> sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}

}