package com.solera.codechallenge.VaccinationManagementSystem.Exception;

@SuppressWarnings("serial")
public class noUsersVaccinated extends RuntimeException {

	public noUsersVaccinated() {

	}

	public noUsersVaccinated(String message) {
		super(message);
	}

	public noUsersVaccinated(Throwable cause) {
		super(cause);
	}

	public noUsersVaccinated(String message, Throwable cause) {
		super(message, cause);
	}
}
