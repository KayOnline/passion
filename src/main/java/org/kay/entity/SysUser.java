package org.kay.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@EntityListeners({ AuditingEntityListener.class })
@Table(name = "SYS_USER")
public class SysUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userCode;
	private String userName;
	private String password;
	private String enabledFlag;
	private String mobilephone;
	private String email;
	private SysUserGroup sysUserGroup;
	private Set<SysRoleUser> sysRoleUsers = new HashSet<SysRoleUser>(0);

	
	// Audit
	@CreatedBy
	private String createdBy;
	@CreatedDate
	private Date createdDate;
	@LastModifiedBy
	private String lastModifiedBy;
	@LastModifiedDate
	private Date lastModifiedDate;

	public SysUser() {
	}

	@Column(name="CREATE_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="LAST_MODIFIED_BY")
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "LAST_MODIFIED_DATE")
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Id
	// @GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID")
	public SysUserGroup getSysUserGroup() {
		return this.sysUserGroup;
	}

	public void setSysUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
	}

	@Column(name = "USER_CODE", length = 32)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "USER_NAME", length = 64)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD", length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "MOBILEPHONE", length = 16)
	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Column(name = "ENABLED", length = 1)
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	@Column(name = "EMAIL", length = 32)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<SysRoleUser> getSysRoleUsers() {
		return this.sysRoleUsers;
	}

	public void setSysRoleUsers(Set<SysRoleUser> sysRoleUsers) {
		this.sysRoleUsers = sysRoleUsers;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (SysRoleUser sysRoleUser : this.sysRoleUsers) {
			String roleCode = sysRoleUser.getSysRole().getRoleCode();
			GrantedAuthority authority = new SimpleGrantedAuthority(roleCode);
			authorities.add(authority);
		}
		return authorities;
	}

	@Override
	@Transient
	public String getUsername() {
		return this.userName;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return this.enabledFlag != null && this.enabledFlag.equals("Y");
	}

	@Override
	public int hashCode() {
		return this.userCode.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SysUser) {
			SysUser sysUser = (SysUser) obj;
			return this.userCode.equals(sysUser.getUserCode());
		}
		return false;
	}

}