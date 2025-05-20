package com.solera.codechallenge.VaccinationManagementSystem.Dao;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import com.solera.codechallenge.VaccinationManagementSystem.Entity.Vaccination;

public interface VaccineDao {
	public List<Vaccination> vaccineddetails();
	 public Vaccination newcitizen(Vaccination vacc) throws ParseException;
	 public Vaccination findbyid(long aadharnum);
	  public String deletebyid(long aadharnum);
	  public Vaccination changestatus(long aadharnum,Vaccination vacc);
	 public List<String> vaccinenames();
	public List<Vaccination> vaccinedetailsbyName(String vaccine_name);
	
}
