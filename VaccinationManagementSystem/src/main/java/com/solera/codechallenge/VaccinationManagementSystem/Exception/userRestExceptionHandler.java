package com.solera.codechallenge.VaccinationManagementSystem.Exception;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class userRestExceptionHandler {
	
	// Exception Handler for CustomerNotFoundException
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
	Date date ;
		@ExceptionHandler
		public ResponseEntity<userErrorResponse> handleException(userNotFoundException cnfe){
			
			// Creating EmployeeErrorResponse object
			
			userErrorResponse error = new userErrorResponse(
												HttpStatus.NOT_FOUND.value(),
												cnfe.getMessage(), simpleDateFormat.format(new Date(System.nanoTime())));
			
			
			// return ResponseEntity
			
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND) ;
		}
		
		// Exception Handler for to catch ANY EXCEPTION
		@ExceptionHandler
		public ResponseEntity<userErrorResponse> handleException(Exception e){
			
			// Creating EmployeeErrorResponse object
			
			userErrorResponse error = new userErrorResponse(
												HttpStatus.BAD_REQUEST.value(),
												e.getMessage(),
												simpleDateFormat.format(new Date(System.nanoTime())));
			
			
			
			// return ResponseEntity
			
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST) ;
		}

}
