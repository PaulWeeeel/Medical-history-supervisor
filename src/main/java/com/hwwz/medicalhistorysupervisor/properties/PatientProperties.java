package com.hwwz.medicalhistorysupervisor.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 15:21
 */
@ConfigurationProperties
public class PatientProperties {

	private Integer id;

	private String name;

	private Boolean gender;

	private Integer age;

	private String address;

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
