package org.kay.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARTY")
public class Party implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long partyId;
	private String partyType;
	private String description;
	private String createBy;
	private Timestamp createdDate;
	private String lastModifiedBy;
	private Timestamp lastModifiedDate;

	public Party() {
	}
	
	public Party(Long partyId, String partyType) {
		this.partyId = partyId;
		this.partyType = partyType;
	}

	@Id
	@Column(name = "PARTY_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "PARTY_TYPE", length = 8)
	public String getPartyType() {
		return this.partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	@Column(name = "DESCRIPTION", length = 256)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CREATE_BY", length = 64)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CREATED_DATE", length = 11)
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "LAST_MODIFIED_BY", length = 64)
	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Column(name = "LAST_MODIFIED_DATE", length = 11)
	public Timestamp getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}