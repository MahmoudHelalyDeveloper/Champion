package com.app.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExecption extends  RuntimeException { 
	   private static final long serialVersionUID = 1L;

	private String messgae;
	
	
	 public ApiExecption() {
		
	}

	public ApiExecption(String messgae) {
		super();
		this.messgae = messgae;
	}

	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
         List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.getMessage(), details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
