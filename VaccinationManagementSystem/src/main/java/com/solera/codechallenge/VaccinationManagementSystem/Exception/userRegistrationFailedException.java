package com.solera.codechallenge.VaccinationManagementSystem.Exception;

@SuppressWarnings("serial")
public class userRegistrationFailedException extends RuntimeException {

	public userRegistrationFailedException() {

	}

	public userRegistrationFailedException(String message) {
		super(message);
	}

	public userRegistrationFailedException(Throwable cause) {
		super(cause);
	}

	public userRegistrationFailedException(String message, Throwable cause) {
		super(message, cause);
	}
}
