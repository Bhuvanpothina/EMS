package com.lb.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;

	@Column(nullable = false, unique = true)
	private String title;

	@Column(name = "salary_band")
	private String salaryBand;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSalaryBand() {
		return salaryBand;
	}

	public void setSalaryBand(String salaryBand) {
		this.salaryBand = salaryBand;
	}

	public Role(Integer roleId, String title, String salaryBand) {
		super();
		this.roleId = roleId;
		this.title = title;
		this.salaryBand = salaryBand;
	}

	public Role() {
		super();
	}

}
