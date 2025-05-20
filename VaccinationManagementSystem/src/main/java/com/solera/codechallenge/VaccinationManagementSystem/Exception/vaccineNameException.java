package com.solera.codechallenge.VaccinationManagementSystem.Exception;

@SuppressWarnings("serial")
public class vaccineNameException extends RuntimeException {
	public vaccineNameException() {

	}

	public vaccineNameException(String message) {
		super(message);
	}

	public vaccineNameException(Throwable cause) {
		super(cause);
	}

	public vaccineNameException(String message, Throwable cause) {
		super(message, cause);
	}

}
