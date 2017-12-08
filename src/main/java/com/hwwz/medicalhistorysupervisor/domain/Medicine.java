package com.hwwz.medicalhistorysupervisor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * @author: Aliweea
 * @date: 2017/12/3/003 23:43
 */
@Entity
public class Medicine {

	private Integer id;

	private String medicine;

	private String dosage;

	private String administration;

	private Integer totalDose;

	private String unit;

	private CaseHistory caseHistory;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Size(max = 40, message = "药品名称的长度应该不超过20个中文字符")
	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	@Size(max = 30, message = "长度应该不超过15个中文字符")
	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	@Size(max = 30, message = "长度应该不超过15个中文字符")
	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}

	@Max(value = 200)
	public Integer getTotalDose() {
		return totalDose;
	}

	public void setTotalDose(Integer totalDose) {
		this.totalDose = totalDose;
	}

	@Size(max = 10, message = "长度应该不超过5个中文字符")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@ManyToOne()
	public CaseHistory getCaseHistory() {
		return caseHistory;
	}

	@JsonBackReference
	public void setCaseHistory(CaseHistory caseHistory) {
		this.caseHistory = caseHistory;
	}
}
