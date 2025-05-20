package com.solera.codechallenge.VaccinationManagementSystem.Exception;

@SuppressWarnings("serial")
public class userNotFullyVaccinated extends RuntimeException {
	public userNotFullyVaccinated() {

	}

	public userNotFullyVaccinated(String message) {
		super(message);
	}

	public userNotFullyVaccinated(Throwable cause) {
		super(cause);
	}

	public userNotFullyVaccinated(String message, Throwable cause) {
		super(message, cause);
	}

}
