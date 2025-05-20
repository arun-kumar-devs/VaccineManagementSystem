package com.solera.codechallenge.VaccinationManagementSystem.Exception;

@SuppressWarnings("serial")
public class userAlreadyExistException extends RuntimeException {

	public userAlreadyExistException() {

	}

	public userAlreadyExistException(String message) {
		super(message);
	}

	public userAlreadyExistException(Throwable cause) {
		super(cause);
	}

	public userAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
