package com.hwwz.medicalhistorysupervisor.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 14:34
 */
@Entity
public class Patient {

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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

}
