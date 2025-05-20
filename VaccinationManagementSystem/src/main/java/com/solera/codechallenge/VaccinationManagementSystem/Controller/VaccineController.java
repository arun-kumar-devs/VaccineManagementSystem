package com.solera.codechallenge.VaccinationManagementSystem.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solera.codechallenge.VaccinationManagementSystem.Entity.Vaccination;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.statusUpdateFailedException;
import com.solera.codechallenge.VaccinationManagementSystem.Exception.statuschangeFailedException;

import com.solera.codechallenge.VaccinationManagementSystem.Service.VaccineServiceImpl;

@RestController
@RequestMapping("/api")
public class VaccineController {

	private VaccineServiceImpl vaccineservice;

	@Autowired
	public VaccineController(VaccineServiceImpl thevaccineservice) {
		vaccineservice = thevaccineservice;
	}
    // request mapping  for new citizen registration
	@PostMapping("/vaccinatedcitizens")
	public Vaccination newregistration(@RequestBody Vaccination vacc) throws Exception {

		return vaccineservice.newregister(vacc);

	}

	// request mapping for list of citizens who are partiallyvaccinated,fullyvaccinated and booster taken
	@GetMapping("/vaccinatedcitizens")
	public List<Vaccination> vaccinedcitizens() {
		return vaccineservice.vaccinedcitizens();

	}

	
	//request mapping for count wise report of every vaccine
	@GetMapping("/vaccinatedcitizens/count")
	public List<String> noOfcitizensvaccined() {
		List<String>vacci = vaccineservice.noOfcitizensvaccined();

		return vacci;
	}

	//request mapping for list of citizens as per vaccine name
	@GetMapping("/vaccinatedcitizens/{vaccine_name}")
	public List<Vaccination> citizensByName(@PathVariable String vaccine_name) {
		return vaccineservice.vaccinedcitizens(vaccine_name);

	}
	
    //request mapping for finding citizen by aadharnum
	@GetMapping("/vaccinatedcitizen/{aadharnum}")
	public Vaccination findcitizen(@PathVariable long aadharnum) {
		return vaccineservice.findcitizen(aadharnum);

	}

	
	// request mapping  for changing vaccinestatus of citizen
	@PutMapping("/statusupdate/{aadharnum}")
	public Vaccination statusupdate(@PathVariable long aadharnum,
			@RequestBody Vaccination vacc) throws statusUpdateFailedException {
		Vaccination vacci = vaccineservice.changestatus(aadharnum, vacc);

		if (!vacci.getVacc_status().equals(vacc.getVacc_status()))
			throw new statuschangeFailedException("unable able to change the status");
		return vacci;
	}

	//requestmapping for deleting citizen by aadharnum
	@DeleteMapping("/removecitizen/{aadharnum}")
	public String deletecitizen(@PathVariable long aadharnum) {
		return vaccineservice.deletecitizen(aadharnum);

	}

}
