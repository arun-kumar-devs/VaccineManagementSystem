package com.solera.codechallenge.VaccinationManagementSystem.Service;



import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solera.codechallenge.VaccinationManagementSystem.Dao.VaccineDao;
import com.solera.codechallenge.VaccinationManagementSystem.Dao.VaccineDaoImpl;
import com.solera.codechallenge.VaccinationManagementSystem.Entity.Vaccination;

import com.solera.codechallenge.VaccinationManagementSystem.Exception.statusUpdateFailedException;

import com.solera.codechallenge.VaccinationManagementSystem.Exception.userNotFoundException;

import com.solera.codechallenge.VaccinationManagementSystem.Exception.userRegistrationFailedException;


@Service
public class VaccineServiceImpl {

	private VaccineDao vaccinedao;
    private Vaccination vacci;
	@Autowired
	public VaccineServiceImpl(VaccineDaoImpl vaccinedao) {
		this.vaccinedao = vaccinedao;
	}

	public static int checkAge(Date dob) {
		
		java.util.Date dob1 = new java.util.Date(dob.getTime());
		LocalDate Dob = dob1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate curDate = LocalDate.now();
		int a = Period.between(Dob, curDate).getYears();
		return a;
	}

	public static long checkDoseGap(Date vaccdosedate1, Date vaccdosedate2) throws Exception {

		java.util.Date d1 = null, d2 = null;
		long noOfDays = 0;
		

		if (vaccdosedate1 != null) {
			
			d1=new java.util.Date(vaccdosedate1.getTime());
		} 
		if (vaccdosedate2 != null) {
			
			d2=new java.util.Date(vaccdosedate2.getTime());
		}
		
		if (vaccdosedate1 != null && vaccdosedate2 != null)
			noOfDays = (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
		
		return noOfDays;
	}


	     @Transactional
		public List<String> noOfcitizensvaccined() {
	    	 
	    	/* int size=0,i=0;
	    	 List<String> vacc=vaccinedao.vaccinenames();
	    	System.out.println(vacc);
	    	System.out.println(vacc.size());
	    	 List<String> result=new ArrayList<String>();
	    	 for(String vaccin:vacc)
	    	 {
	    		if(vaccin!=null)
	    		{
			          List<Vaccination> vacci = vaccinedao.vaccinedetailsbyName(vaccin);
			           Vaccination vaccine=vacci.get(i++);
			            size=vacci.size();
			           if(size!=0)
			                 result.add("no of citizens vaccinated for "+vaccine.getVacc_name()+ "  "+"are" + " " +size);
			           else 
			        	   throw new userNotFoundException("No user details not found in Database");
	    	     }
	    		else 
	    			continue;
	    		}*/
	    	 List<String> result=vaccinedao.vaccinenames();
			return result;
			
		}
	  
    @Transactional
	public List<Vaccination> vaccinedcitizens(String vaccine_name) {
		List<Vaccination> vacci = vaccinedao.vaccinedetailsbyName(vaccine_name);
		if(vacci.isEmpty())
			throw new userNotFoundException("No user details not found in Database");
		else
		    return vacci;
	}
    @Transactional
	public Vaccination newregister(Vaccination vacc) throws Exception {
		Vaccination vaccin = vaccinedao.newcitizen(vacc);
		if(vaccin==null)
			throw new userRegistrationFailedException("unable to register user");
		else
			return vaccin;
		
	}
    @Transactional
	public List<Vaccination> vaccinedcitizens() {
		List<Vaccination> vacci = vaccinedao.vaccineddetails();
		if(vacci.isEmpty())
			throw new userNotFoundException("No user details not found in Database");
		else
		  return vacci;
	}
    @Transactional
	public Vaccination findcitizen(long aadharnum) {
		Vaccination vacci = vaccinedao.findbyid(aadharnum);
		if (vacci == null)
			throw new userNotFoundException("user not exist");
		else 
			return vacci;

		
	}
    @Transactional
	public Vaccination changestatus(long aadharnum, Vaccination vacc)  throws statusUpdateFailedException{

    	
    	 vacci=vaccinedao.changestatus(aadharnum,vacc);
    	 if(vacci==null)
    		 throw new statusUpdateFailedException("status updation failed");
    	 return vacci;
		
	}
    @Transactional
	public String deletecitizen(long aadharnum) {
		String result = vaccinedao.deletebyid(aadharnum);
		if(result.isEmpty())
			throw new userNotFoundException("No user details not found in Database");
		else
		     return result;
	}
}
