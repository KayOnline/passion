package org.kay.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARTY_RELATIONSHIP")
public class PartyRelationship implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "RELATIONSHIP_ID")
	private Long relationshipId;
	@Column(name = "RELATIONSHIP_TYPE")
	private String relationshipType;
	@Column(name = "PARTY_ID_FROM")
	private Long partyIdFrom;
	@Column(name = "PARTY_ID_TO")
	private Long partyIdTo;
	@Column(name = "ROLE_ID_FROM")
	private Long roleIdFrom;
	@Column(name = "ROLE_ID_TO")
	private Long roleIdTo;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;
	@Column(name = "LAST_MODIFIED_BY")
	private String lastModifiedBy;
	@Column(name = "LAST_MODIFIED_DATE")
	private Timestamp lastModifiedDate;

	/** default constructor */
	public PartyRelationship() {
	}

	public Long getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(Long relationshipId) {
		this.relationshipId = relationshipId;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public Long getPartyIdFrom() {
		return partyIdFrom;
	}

	public void setPartyIdFrom(Long partyIdFrom) {
		this.partyIdFrom = partyIdFrom;
	}

	public Long getPartyIdTo() {
		return partyIdTo;
	}

	public void setPartyIdTo(Long partyIdTo) {
		this.partyIdTo = partyIdTo;
	}

	public Long getRoleIdFrom() {
		return roleIdFrom;
	}

	public void setRoleIdFrom(Long roleIdFrom) {
		this.roleIdFrom = roleIdFrom;
	}

	public Long getRoleIdTo() {
		return roleIdTo;
	}

	public void setRoleIdTo(Long roleIdTo) {
		this.roleIdTo = roleIdTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}