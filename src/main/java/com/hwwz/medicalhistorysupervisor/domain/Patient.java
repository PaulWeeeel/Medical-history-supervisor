package com.hwwz.medicalhistorysupervisor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * @author: Aliweea
 * @date: 2017/12/1/001 14:34
 */
@Entity
public class Patient {

	private Integer id;

	private String name;

	private Boolean gender;

	private Integer age;

	private String address;

	private String occupation;

	private String phone;

	private Double totalFee;

	private String lastDate;

	private List<CaseHistory> caseHistoryList;

	private String photoURL;

	private String faceToken;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank(message = "姓名不能为空")
	@Size(max = 20, message = "姓名的长度应该不超过10个中文字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull(message = "性别不能为空")
	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	@Max(value = 300, message = "年龄不能超过300岁")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Size(max = 100, message = "地址的长度应该不超过50个中文字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Size(max = 20, message="职业的长度应该不超过10个中文字符")
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    @Size(max = 13, message="手机号的长度应该不超过13个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(mappedBy = "patient")
	public List<CaseHistory> getCaseHistoryList() {
		return caseHistoryList;
	}

    @JsonBackReference
	public void setCaseHistoryList(List<CaseHistory> caseHistoryList) {
		this.caseHistoryList = caseHistoryList;
	}

	public String findLastTime() {
        return lastDate;
    }

    public CaseHistory findLastCaseHistory() {
        return caseHistoryList.get(caseHistoryList.size()-1);
    }

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getFaceToken() { return faceToken; }

	public void setFaceToken(String faceToken) { this.faceToken = faceToken; }
}
