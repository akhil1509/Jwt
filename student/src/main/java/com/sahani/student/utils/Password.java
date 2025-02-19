package com.sahani.student.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {
	public static void main(String[] args) {
		PasswordEncoder pass = new BCryptPasswordEncoder();
		//pass.encode("akhilesh");
		
		System.out.println(pass.encode("sahani"));
		System.out.println(pass.encode("Akhilesh"));
	}

}
