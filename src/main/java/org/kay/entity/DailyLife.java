package org.kay.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
@Table(name = "DAILY_LIFE")
public class DailyLife implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer mon;
	private Timestamp day;
	private String drinkWater;
	private BigDecimal readTime;
	private Integer workOvertimeHours;
	private BigDecimal weight;
	private Integer situpNum;
//	private Set<DailyCost> dailyCosts = new HashSet<DailyCost>(0);

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "MON", precision = 6, scale = 0)
	public Integer getMon() {
		return this.mon;
	}

	public void setMon(Integer mon) {
		this.mon = mon;
	}

	@Column(name = "DAY", length = 7)
	public Timestamp getDay() {
		return this.day;
	}

	public void setDay(Timestamp day) {
		this.day = day;
	}

	@Column(name = "DRINK_WATER", length = 1)
	public String getDrinkWater() {
		return this.drinkWater;
	}

	public void setDrinkWater(String drinkWater) {
		this.drinkWater = drinkWater;
	}

	@Column(name = "READ_TIME", precision = 3, scale = 1)
	public BigDecimal getReadTime() {
		return this.readTime;
	}

	public void setReadTime(BigDecimal readTime) {
		this.readTime = readTime;
	}

	@Column(name = "WORK_OVERTIME_HOURS", precision = 2, scale = 0)
	public Integer getWorkOvertimeHours() {
		return this.workOvertimeHours;
	}

	public void setWorkOvertimeHours(Integer workOvertimeHours) {
		this.workOvertimeHours = workOvertimeHours;
	}

	@Column(name = "WEIGHT", precision = 3, scale = 1)
	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Column(name = "SITUP_NUM", precision = 4, scale = 0)
	public Integer getSitupNum() {
		return this.situpNum;
	}

	public void setSitupNum(Integer situpNum) {
		this.situpNum = situpNum;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dailyLife")
//	public Set<DailyCost> getDailyCosts() {
//		return this.dailyCosts;
//	}
//
//	public void setDailyCosts(Set<DailyCost> dailyCosts) {
//		this.dailyCosts = dailyCosts;
//	}

}