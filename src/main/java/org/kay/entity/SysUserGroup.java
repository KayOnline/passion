package org.kay.entity;

import java.math.BigDecimal;
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
@Table(name = "SYS_USER_GROUP")
public class SysUserGroup implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String groupId;
	private SysUserGroup sysUserGroup;
	private String groupName;
	private String groupType;
	private BigDecimal displayNo;
	private Set<SysUserGroup> sysUserGroups = new HashSet<SysUserGroup>(0);
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);

	// Constructors

	/** default constructor */
	public SysUserGroup() {
	}

	/** minimal constructor */
	public SysUserGroup(String groupId) {
		this.groupId = groupId;
	}

	/** full constructor */
	public SysUserGroup(String groupId, SysUserGroup sysUserGroup, String groupName, String groupType, BigDecimal displayNo, Set<SysUserGroup> sysUserGroups, Set<SysUser> sysUsers) {
		this.groupId = groupId;
		this.sysUserGroup = sysUserGroup;
		this.groupName = groupName;
		this.groupType = groupType;
		this.displayNo = displayNo;
		this.sysUserGroups = sysUserGroups;
		this.sysUsers = sysUsers;
	}

	// Property accessors
	@Id
	@Column(name = "GROUP_ID", unique = true, nullable = false, length = 32)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_PID")
	public SysUserGroup getSysUserGroup() {
		return this.sysUserGroup;
	}

	public void setSysUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
	}

	@Column(name = "GROUP_NAME", length = 64)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "GROUP_TYPE", length = 4)
	public String getGroupType() {
		return this.groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	@Column(name = "DISPLAY_NO", precision = 22, scale = 0)
	public BigDecimal getDisplayNo() {
		return this.displayNo;
	}

	public void setDisplayNo(BigDecimal displayNo) {
		this.displayNo = displayNo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserGroup")
	public Set<SysUserGroup> getSysUserGroups() {
		return this.sysUserGroups;
	}

	public void setSysUserGroups(Set<SysUserGroup> sysUserGroups) {
		this.sysUserGroups = sysUserGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUserGroup")
	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

}