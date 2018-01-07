package com.hwwz.medicalhistorysupervisor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/3/003 22:18
 */
@Entity
public class Disease {

	private String disease;

	private Integer level;

	private Integer riskDegree;

	private List<CaseHistory> caseHistoryList;

	@Id
	@Size(max = 40, message = "疾病名称不能超过20个中文字符")
	public String getDisease() {
		return disease;
	}

	public void setDisease(String diseaseName) {
		this.disease = diseaseName;
	}


	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getRiskDegree() {
		return riskDegree;
	}

	public void setRiskDegree(Integer riskDegree) {
		this.riskDegree = riskDegree;
	}

	@ManyToMany
    public List<CaseHistory> getCaseHistory() {
        return caseHistoryList;
    }

    @JsonIgnore
    public void setCaseHistory(List<CaseHistory> caseHistoryList) {
        this.caseHistoryList = caseHistoryList;
    }


}
