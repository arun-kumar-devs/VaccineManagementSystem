package com.solera.codechallenge.VaccinationManagementSystem.Service;

import java.util.List;

import com.solera.codechallenge.VaccinationManagementSystem.Entity.Vaccination;

public interface Vaccineservice {

	public Vaccination newregister(Vaccination vacc);
	public List<Vaccination> vaccinedcitizens();
	public Vaccination findcitizen(long aadharnum);
	public Vaccination changestatus(long aaharnum,String vaccine_status,Vaccination vacc);
	public String deletecitizen(long aadharnum);
}
