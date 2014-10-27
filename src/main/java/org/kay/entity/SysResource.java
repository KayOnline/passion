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
@Table(name = "SYS_RESOURCE")
public class SysResource implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String resourceId;
	private SysResource sysResource;
	private String resourceName;
	private String resourceType;
	private String resourceUrl;
	private Set<SysResource> sysResources = new HashSet<SysResource>(0);
	private Set<SysPrivilege> sysPrivileges = new HashSet<SysPrivilege>(0);

	public SysResource() {
	}

	@Id
	@Column(name = "RESOURCE_ID", unique = true, nullable = false, length = 32)
	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESOURCE_PID")
	public SysResource getSysResource() {
		return this.sysResource;
	}

	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}

	@Column(name = "RESOURCE_NAME", length = 64)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "RESOURCE_TYPE", length = 4)
	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "RESOURCE_URL", length = 256)
	public String getResourceUrl() {
		return this.resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysResource")
	public Set<SysResource> getSysResources() {
		return this.sysResources;
	}

	public void setSysResources(Set<SysResource> sysResources) {
		this.sysResources = sysResources;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysResource")
	public Set<SysPrivilege> getSysPrivileges() {
		return this.sysPrivileges;
	}

	public void setSysPrivileges(Set<SysPrivilege> sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}

}