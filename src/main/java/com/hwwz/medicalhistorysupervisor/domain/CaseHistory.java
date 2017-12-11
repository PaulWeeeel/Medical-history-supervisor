package com.hwwz.medicalhistorysupervisor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
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

    private String imageUrl;

	private Timestamp onset;

	private Timestamp dateTime;

	private Patient patient;

	private List<MedicineRecord> medicineRecordList;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

	public Timestamp getOnset() {
		return onset;
	}

	public void setOnset(Timestamp onset) {
		this.onset = onset;
	}

    public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	@ManyToOne()
	public Patient getPatient() {
		return patient;
	}

	@JsonBackReference
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@OneToMany(mappedBy = "caseHistory")
	public List<MedicineRecord> getMedicineRecordList() {
		return medicineRecordList;
	}

	@JsonBackReference
	public void setMedicineRecordList(List<MedicineRecord> medicineRecordList) {
		this.medicineRecordList = medicineRecordList;
	}
}
