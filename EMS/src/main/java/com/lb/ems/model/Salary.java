package com.lb.ems.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salaries")
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salaryId;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	@Column(nullable = false)
	private BigDecimal amount;

	@Column(name = "effectiveDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date effectiveDate;

	@Column(name = "endDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Salary(Integer salaryId, Employee employee, BigDecimal amount, Date effectiveDate, Date endDate) {
		super();
		this.salaryId = salaryId;
		this.employee = employee;
		this.amount = amount;
		this.effectiveDate = effectiveDate;
		this.endDate = endDate;
	}

	public Salary() {
		super();
	}

}
