package com.hwwz.medicalhistorysupervisor.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:11
 */
@Entity
public class User {

	private String username;

	private String password;

	@Id
	@Size(max = 20, message = "用户名应该不超过10个中文字符或20个英文字符")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Size(max = 20, message = "密码应该不超过10个中文字符或20个英文字符")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
