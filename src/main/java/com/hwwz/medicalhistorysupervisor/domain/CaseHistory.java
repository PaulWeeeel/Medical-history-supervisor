package com.hwwz.medicalhistorysupervisor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/3/003 21:09
 */
@Entity
public class CaseHistory {

	private Integer id;

	private String onset;

    private Double fee;

	private String dateTime;

	private String description;

	private Patient patient;

	private List<Disease> diseaseList;

	private List<SymptomFigure> imageUrlList;

    private List<MedicineRecord> medicineRecordList;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOnset() {
		return onset;
	}

	public void setOnset(String onset) {
		this.onset = onset;
	}

    public String getDateTime() {
		return dateTime;
	}

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
	public Patient getPatient() {
		return patient;
	}

	@JsonBackReference
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToMany
    @JoinTable(joinColumns = { @JoinColumn(name = "caseHistory_id") }, inverseJoinColumns = {
            @JoinColumn(name = "disease_id") })
    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    @JsonBackReference
    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    @OneToMany(mappedBy = "caseHistory")
    public List<SymptomFigure> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<SymptomFigure> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    @OneToMany(mappedBy = "caseHistory")
	public List<MedicineRecord> getMedicineRecordList() {
		return medicineRecordList;
	}

	@JsonBackReference
	public void setMedicineRecordList(List<MedicineRecord> medicineRecordList) {
		this.medicineRecordList = medicineRecordList;
	}

	public String findFormatDateTime() {
	    return dateTime.substring(2, 10);
    }

    public String findDiseaseNames() {
	    StringBuffer stringBuffer = new StringBuffer();
        for (Disease disease : diseaseList) {
            stringBuffer.append(disease.getDisease() + ", ");
        }
        int length = stringBuffer.length();
        if (length != 0) {
            stringBuffer.delete(stringBuffer.length()-2, stringBuffer.length()-1);
        }
        return stringBuffer.toString();
    }

	public String findMedicineNames() {
        StringBuffer stringBuffer = new StringBuffer();
        for (MedicineRecord medicineRecord : medicineRecordList) {
            stringBuffer.append(medicineRecord.getMedicine() + ", ");
        }
        int length = stringBuffer.length();
        if (length != 0) {
            stringBuffer.delete(stringBuffer.length()-2, stringBuffer.length()-1);
        }
        return stringBuffer.toString();
    }

    public String findMedicineInfos() {
        StringBuffer stringBuffer = new StringBuffer();
        for (MedicineRecord medicineRecord : medicineRecordList) {
            stringBuffer.append(medicineRecord.getMedicine() + medicineRecord.getTotalDose() + "粒, ");
        }
        int length = stringBuffer.length();
        if (length != 0) {
            stringBuffer.delete(stringBuffer.length()-2, stringBuffer.length()-1);
        }
        return stringBuffer.toString();
    }
}
