package com.solera.codechallenge.VaccinationManagementSystem.Exception;

@SuppressWarnings("serial")
public class notValidAgeException extends RuntimeException {
	
	public notValidAgeException()
	{
		
	}
	public notValidAgeException(String message)
	{
		super(message);
	}
	public notValidAgeException(Throwable cause)
	{
		super(cause);
	}
	
	public notValidAgeException(String message,Throwable cause)
	{
		super(message,cause);
	}

}
