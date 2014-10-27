package org.kay.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "ORGANIZATION")
public class Organization implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long partyId;
	private String groupCode;
	private String groupName;
	private String groupLevel;
	private String groupType;
	private String description;
	private String createBy;
	private Timestamp createdDate;
	private String lastModifiedBy;
	private Timestamp lastModifiedDate;

	/** default constructor */
	public Organization() {
	}

	@Id
	@Column(name = "PARTY_ID")
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "GROUP_CODE")
	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	@Column(name = "GROUP_NAME")
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "GROUP_LEVEL")
	public String getGroupLevel() {
		return this.groupLevel;
	}

	public void setGroupLevel(String groupLevel) {
		this.groupLevel = groupLevel;
	}

	@Column(name = "GROUP_TYPE")
	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CREATE_BY")
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CREATED_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "LAST_MODIFIED_BY")
	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Column(name = "LAST_MODIFIED_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Timestamp getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}