package com.sahani.student.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	
	private String usernameoremail;
	private String password;
	
	public String getUsernameoremail() {
		return usernameoremail;
	}
	public void setUsernameoremail(String usernameoremail) {
		this.usernameoremail = usernameoremail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
