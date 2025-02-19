package com.sahani.student.exception;

public class ResouceNotFoundException extends RuntimeException {

	public ResouceNotFoundException() {
		super("Student is not available on current id");
	}
	
	
	public ResouceNotFoundException(String message) {
		super(message);
	}

}
