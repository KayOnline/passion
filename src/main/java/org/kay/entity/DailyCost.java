package org.kay.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "DAILY_COST")
@DynamicUpdate
@DynamicInsert
@NamedStoredProcedureQuery(
	name = "EMP_READ_ALL",
	procedureName = "EMP_READ_ALL1",
	parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name="paramin")
	})
public class DailyCost implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String costType;
	private BigDecimal costMoney;
	private String remark;

	public DailyCost() {
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_DAILY_LIFE", allocationSize = 1)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "COST_TYPE")
	public String getCostType() {
		return this.costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	@Column(name = "COST_MONEY")
	public BigDecimal getCostMoney() {
		return this.costMoney;
	}

	public void setCostMoney(BigDecimal costMoney) {
		this.costMoney = costMoney;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}