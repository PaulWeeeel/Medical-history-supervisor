package com.hwwz.medicalhistorysupervisor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/3/003 21:09
 */
@Entity
public class CaseHistory {

	private Integer id;

	private String disease;

	private Timestamp onset;

	private String details;

	private  Integer level;

	private Timestamp dateTime;

	private Patient patient;

	private List<Medicine> medicineList;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank(message = "疾病名称不能为空")
	@Size(max = 100, message = "疾病名称的长度应该不超过50个中文字符")
	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Timestamp getOnset() {
		return onset;
	}

	public void setOnset(Timestamp onset) {
		this.onset = onset;
	}

	@Size(max = 300, message = "长度应该不超过150个中文字符")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Min(value = 1, message = "等级不能低于1")
	@Max(value = 10, message = "等级不能高于10")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	@ManyToOne(optional = false)
	public Patient getPatient() {
		return patient;
	}

	@JsonBackReference
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@OneToMany(mappedBy = "caseHistory", fetch = FetchType.LAZY)
	public List<Medicine> getMedicineList() {
		return medicineList;
	}

	@JsonBackReference
	public void setMedicineList(List<Medicine> medicineList) {
		this.medicineList = medicineList;
	}
}
