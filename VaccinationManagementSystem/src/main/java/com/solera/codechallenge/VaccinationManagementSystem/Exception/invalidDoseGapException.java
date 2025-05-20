package com.solera.codechallenge.VaccinationManagementSystem.Exception;

@SuppressWarnings("serial")
public class invalidDoseGapException extends RuntimeException {

	public invalidDoseGapException()
	{
		
	}
	public invalidDoseGapException(String message)
	{
		super(message);
	}
	public invalidDoseGapException(Throwable cause)
	{
		super(cause);
	}
	public invalidDoseGapException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
