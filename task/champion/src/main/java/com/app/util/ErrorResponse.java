package com.app.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorResponse{

	
	
	
	public ErrorResponse() {
		super();
	}

	

	private String message; 
	
	private List<String> details ;
	
	
	
	

	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}



	private Date time = new Date();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
