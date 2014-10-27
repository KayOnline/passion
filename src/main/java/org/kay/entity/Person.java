package org.kay.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "PERSON")
@DynamicUpdate
@DynamicInsert
public class Person implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long partyId;
	private String personCode;
	private String personName;
	private String personNameSpell;
	private String personStatus;
	private String gender;
	private String nationality;
	private String politicalStatus;
	@Column(name = "BIRTHDAY")
	private Timestamp birthday;
	private String description;
	private String createBy;
	private Timestamp createdDate;
	private String lastModifiedBy;
	private Timestamp lastModifiedDate;

	/** default constructor */
	public Person() {
	}

	@Id
	@Column(name = "PARTY_ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "PERSON_CODE", length = 16)
	public String getPersonCode() {
		return this.personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	@Column(name = "PERSON_NAME", nullable = false, length = 32)
	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Column(name = "PERSON_NAME_SPELL", length = 64)
	public String getPersonNameSpell() {
		return this.personNameSpell;
	}

	public void setPersonNameSpell(String personNameSpell) {
		this.personNameSpell = personNameSpell;
	}

	@Column(name = "GENDER", length = 8)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "NATIONALITY", length = 8)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "POLITICAL_STATUS", length = 8)
	public String getPoliticalStatus() {
		return this.politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
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

	@Column(name = "PERSON_STATUS")
	public String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

}