package com.hwwz.medicalhistorysupervisor.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author: Aliweea
 * @date: 2017/12/3/003 22:18
 */
@Entity
public class Disease {

	private String disease;

	private Integer level;

	private Integer risk_degree;

	@Id
	@Size(max = 40, message = "疾病名称不能超过20个中文字符")
	public String getDisease() {
		return disease;
	}

	public void setDisease(String diseaseName) {
		this.disease = diseaseName;
	}

	@Min(value = 1, message = "等级不能低于1")
	@Max(value = 10, message = "等级不能高于10")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Min(value = 1, message = "等级不能低于1")
	@Max(value = 10, message = "等级不能高于10")
	public Integer getRisk_degree() {
		return risk_degree;
	}

	public void setRisk_degree(Integer risk_degree) {
		this.risk_degree = risk_degree;
	}
}
