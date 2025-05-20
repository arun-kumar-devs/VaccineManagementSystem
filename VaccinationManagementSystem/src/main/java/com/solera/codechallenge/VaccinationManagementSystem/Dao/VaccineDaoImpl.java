package com.solera.codechallenge.VaccinationManagementSystem.Dao;


import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.solera.codechallenge.VaccinationManagementSystem.Entity.Vaccination;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.invalidDoseGapException;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.notValidAgeException;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.statuschangeFailedException;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.userAlreadyExistException;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.userDeletionNotPossibleException;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.userNotFoundException;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.userNotFullyVaccinated;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.userRegistrationFailedException;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.vaccineNameException;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.vaccinedetailsShouldNotEnterException;
import com.solera.codechallenge.VaccinationManagementSystem.Service.VaccineServiceImpl;

@Repository
public class VaccineDaoImpl implements VaccineDao {
	private EntityManager entitymanager;
	List<String> statuses = new ArrayList<String>();
	{
		statuses.add("fullyvaccinated");
		statuses.add("partiallyvaccinated");
		statuses.add("boostervaccinated");
	}

	@Autowired
	public VaccineDaoImpl(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	public List<String> vaccinenames()
	{
		int size=0,i=0;
		Session currentsession = entitymanager.unwrap(Session.class);
		Query thequery=currentsession.createSQLQuery("select distinct vaccine_name from vaccine_details ");
		List<String> vacc=thequery.getResultList();
		
		
   	 
   	
   	 List<String> result=new ArrayList<String>();
   	 for(String vaccin:vacc)
   	 {
   		if(vaccin!=null)
   		{
		          List<Vaccination> vacci = vaccinedetailsbyName(vaccin);
		           Vaccination vaccine=vacci.get(i++);
		            size=vacci.size();
		           if(size!=0)
		                 result.add("no of citizens vaccinated for "+vaccine.getVacc_name()+ "  "+"are" + " " +size);
		           else 
		        	   throw new userNotFoundException("No user details not found in Database");
   	     }
   		else 
   			continue;
   		}
   	 return result;
		
	}
	
	
	@Override
	
	public List<Vaccination> vaccinedetailsbyName(String vaccine_name)
	{
		Session currentsession =entitymanager.unwrap(Session.class);
		Query<Vaccination> thequery=currentsession.createQuery("from Vaccination",Vaccination.class);
		List<Vaccination> vaccinations=thequery.getResultList();
		
		List<Vaccination> result=new ArrayList<Vaccination>();
		for(Vaccination vacc:vaccinations)
		{
			
			if(vacc.getVacc_name()!=null)
				if(vacc.getVacc_name().equalsIgnoreCase(vaccine_name))
					result.add(vacc);
		}
		
		if(result.isEmpty())
			throw new userNotFoundException("no user details found for specified vaccine name");
		return result;
	}
	@Override

	public List<Vaccination> vaccineddetails() {
		try {
			Session currentsession = entitymanager.unwrap(Session.class);
			Query<Vaccination> query = currentsession.createQuery("from Vaccination", Vaccination.class);

			List<Vaccination> queryresult = query.getResultList();
			List<Vaccination> vaccdetails = new ArrayList<Vaccination>();
			
			for (Vaccination vacci : queryresult) {
				if(vacci.getVacc_status()!=null)
				if(statuses.contains(vacci.getVacc_status().toLowerCase()))
						vaccdetails.add(vacci);
			}
			return vaccdetails;
		} catch (Exception e) {
			throw new userNotFoundException(e);
		}
	}

	@Override
	
	public Vaccination newcitizen(Vaccination vacc) {
		
		 int age = 0;
			try {
				age = VaccineServiceImpl.checkAge(vacc.getDob());
				 Session currentsession=entitymanager.unwrap(Session.class);
				Vaccination vacci = currentsession.get(Vaccination.class,vacc.getCitizen_aadharnum());
				if ( vacc.getVacc_d2() != null||vacc.getBoostervacc_date()!=null)
					throw new vaccinedetailsShouldNotEnterException("vaccine details should not enter");
				
				else if (vacci == null)
					{
						if (age > 18) 
						{
							currentsession.save(vacc);
						} 
						else
							throw new notValidAgeException("Age should be greater than 18 but your age is" + age);
					} 
				else
					throw new userAlreadyExistException("user  already exist with this Aadhar number");
		
		
		 Vaccination vaccin = currentsession.get(Vaccination.class, vacc.getCitizen_aadharnum());
		 return vaccin;
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new userRegistrationFailedException(e);
			}
	}

	@Override
	
	public Vaccination findbyid(long aadharnum) {

		try {
			Session currentsession = entitymanager.unwrap(Session.class);
			Vaccination vacci = currentsession.get(Vaccination.class, aadharnum);
			 if (statuses.contains(vacci.getVacc_status().toLowerCase()))
				    return vacci;

			else
				throw new userNotFullyVaccinated("user is not fully vaccinated he is " + vacci.getVacc_status());
			  

		} catch (Exception e) {
			throw new userNotFoundException(e);
		}
	}

	@Override

	public String deletebyid(long aadharnum) {
		try {
			Session currentsession = entitymanager.unwrap(Session.class);
			Vaccination vacci = findbyid(aadharnum) ;
			if(vacci==null)
				throw new userNotFoundException("user not exists");
			else if (vacci.getVacc_status().equalsIgnoreCase("boosterVaccinated")&&vacci.getVacc_noofdoses()==3)
				currentsession.delete(vacci);
			else
				throw new userDeletionNotPossibleException("user deletion not possible because" + vacci.getVacc_status());
			return "deleted successfully";
		} catch (Exception e) {
			throw new userNotFoundException(e);
		}
	}

	@Override
	
	public Vaccination changestatus(long aadharnum,Vaccination vacc) {
		try {
			
			Session currentsession = entitymanager.unwrap(Session.class);
			
			
			long noOfdays = 0, bnoOfdays = 0;
			Date datecanbeboosted=null;
			Vaccination vaccin = null;
			Vaccination vacci = currentsession.find(Vaccination.class, aadharnum);
			if (vacci == null)
				throw new userNotFoundException("user not exist");
			
			LocalDate current = (vacc.getVacc_d1() != null) ? vacc.getVacc_d1().toLocalDate(): vacci.getVacc_d1().toLocalDate();
			if(vacc.getVacc_d2()!=null||vacci.getVacc_d2()!=null)
			{
			LocalDate vaccinedate2 = (vacc.getVacc_d2() != null) ? vacc.getVacc_d2().toLocalDate(): vacci.getVacc_d2().toLocalDate();
			LocalDate boostdate = vaccinedate2.plusDays(270);
			datecanbeboosted = Date.valueOf(boostdate);
			}
			LocalDate vaccdate2 = current.plusDays(120);
			Date datecanbevaccinated = Date.valueOf(vaccdate2);
			
			vacci.setVacc_status(vacc.getVacc_status());
			
			
			vacci.setVacc_noofdoses(vacc.getVacc_noofdoses());
			noOfdays = VaccineServiceImpl.checkDoseGap(vacci.getVacc_d1(), vacc.getVacc_d2());
			bnoOfdays = VaccineServiceImpl.checkDoseGap(vacci.getVacc_d2(), vacc.getBoostervacc_date());
			if (!vacc.getVacc_name().isEmpty() &&vacc.getVacc_d1()!=null &&vacc.getVacc_d2()==null)
			{
				vacci.setVacc_d1(vacc.getVacc_d1());
				vacci.setVacc_name(vacc.getVacc_name());
				currentsession.update(vacci);
			} 
			else if (!vacc.getVacc_name().isEmpty() && vacc.getVacc_d2()!=null&&vacc.getBoostervacc_date()==null)
			{
				if (vacci.getVacc_name().equalsIgnoreCase(vacc.getVacc_name()))
				{
				
					vacci.setVacc_d2(vacc.getVacc_d2());
					if (noOfdays >= 120)
						currentsession.update(vacci);
					else
						throw new invalidDoseGapException(
								"no of days gap between two doses should be greater than 120 then valid date can be "
										+ datecanbevaccinated + "But yours " + vacc.getVacc_d2() + " " + noOfdays);
				}
				else
					throw new vaccineNameException("both doses of vaccines should be same");
			} 
			else if (!vacc.getVacc_name().isEmpty() && vacc.getBoostervacc_date()!=null)
			{
				if (bnoOfdays >= 270&&vacci.getVacc_name().equalsIgnoreCase(vacc.getVacc_name()))
				{
					vacci.setBoostervacc_date(vacc.getBoostervacc_date());
					 currentsession.update(vacci);
				} 
				else
					throw new invalidDoseGapException(
							"no of days gap between dose two and booster dose  should be greater than 270 then valid date can be "
									+ datecanbeboosted + "But yours " + vacc.getBoostervacc_date() + " " + bnoOfdays);
			} 
			else

				throw new vaccineNameException("vaccine name cant be empty");

			
			  vaccin = currentsession.get(Vaccination.class, aadharnum);
			return vaccin;
		} catch (Exception e) {
			throw new statuschangeFailedException(e);
		}
	}
}
