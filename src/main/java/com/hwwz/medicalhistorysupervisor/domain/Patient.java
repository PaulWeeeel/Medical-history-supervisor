package com.hwwz.medicalhistorysupervisor.domain;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
=======
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 14:34
 */
@Entity
public class Patient {

<<<<<<< HEAD
	private Integer id;

	private String name;

	private Boolean gender;

	private Integer age;

	private String address;

	private String occupation;

	private String phone;

	private List<CaseHistory> caseHistoryList;


	@Id
	@GeneratedValue
=======
	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank(message = "姓名不能为空")
	@Size(max = 20, message = "姓名的长度应该不超过10个中文字符")
	private String name;

	@NotNull(message = "性别不能为空")
	private Boolean gender;

	@Max(value = 300, message = "年龄不能超过300岁")
	private Integer age;

	@Size(max = 100, message = "地址的长度应该不超过50个中文字符")
	private String address;

	@Size(max = 20, message="职业的长度应该不超过10个中文字符")
	private String occupation;


>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

<<<<<<< HEAD
	@NotBlank(message = "姓名不能为空")
	@Size(max = 20, message = "姓名的长度应该不超过10个中文字符")
=======
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

<<<<<<< HEAD
	@NotNull(message = "性别不能为空")
=======
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

<<<<<<< HEAD
	@Max(value = 300, message = "年龄不能超过300岁")
=======
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

<<<<<<< HEAD
	@Size(max = 100, message = "地址的长度应该不超过50个中文字符")
=======
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

<<<<<<< HEAD
	@Size(max = 20, message="职业的长度应该不超过10个中文字符")
=======
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

<<<<<<< HEAD
	@Size(max = 13, message="手机号的长度应该不超过13个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	public List<CaseHistory> getCaseHistoryList() {
		return caseHistoryList;
	}

	@JsonIgnore
	public void setCaseHistoryList(List<CaseHistory> caseHistoryList) {
		this.caseHistoryList = caseHistoryList;
	}

	@Override
	public String toString(){
		return "[]";
	}
=======
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
}
