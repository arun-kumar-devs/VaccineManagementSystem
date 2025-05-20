package com.solera.codechallenge.VaccinationManagementSystem.Exception;

@SuppressWarnings("serial")
public class statuschangeFailedException extends RuntimeException {

	public statuschangeFailedException() {

	}

	public statuschangeFailedException(String message) {
		super(message);
	}

	public statuschangeFailedException(Throwable cause) {
		super(cause);
	}

	public statuschangeFailedException(String message, Throwable cause) {
		super(message, cause);
	}

}
