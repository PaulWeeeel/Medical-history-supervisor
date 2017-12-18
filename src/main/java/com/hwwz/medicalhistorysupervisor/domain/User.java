package com.hwwz.medicalhistorysupervisor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:11
 */
@Entity
public class User {

	 private Integer id;

	 private String username;

	 private String password;

	 private String phone;

	 @Id
	 @GeneratedValue
	 public Integer getId() {
	 	return id;
	 }

	 public void setId(Integer id) {
	 	this.id = id;
	 }

	 @Size(max = 20, message = "用户名应该不超过10个中文字符或20个英文字符")
     @NotNull
	 public String getUsername() {
	 	return username;
	 }

	 public void setUsername(String username) {
	 	this.username = username;
	 }

	 @Size(max = 20, message = "密码应该不超过10个中文字符或20个英文字符")
     @NotNull
	 public String getPassword() {
	 	return password;
	 }

	 public void setPassword(String password) {
	 	this.password = password;
	 }

	 @Column(length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
