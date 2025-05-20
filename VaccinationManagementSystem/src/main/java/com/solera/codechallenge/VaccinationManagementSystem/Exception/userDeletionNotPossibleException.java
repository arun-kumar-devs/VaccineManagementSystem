package com.solera.codechallenge.VaccinationManagementSystem.Exception;

@SuppressWarnings("serial")
public class userDeletionNotPossibleException extends RuntimeException {

	public userDeletionNotPossibleException() {

	}

	public userDeletionNotPossibleException(String message) {
		super(message);
	}

	public userDeletionNotPossibleException(Throwable cause) {
		super(cause);
	}

	public userDeletionNotPossibleException(String message, Throwable cause) {
		super(message, cause);
	}

}
