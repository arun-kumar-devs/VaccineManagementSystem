package com.solera.codechallenge.VaccinationManagementSystem.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "citizen_details")
@SecondaryTable(name = "vaccine_details", pkJoinColumns = @PrimaryKeyJoinColumn(name = "citizen_aadharnum", referencedColumnName = "citizen_aadharnum"))
public class Vaccination {

	@Column(name = "vid", table = "vaccine_details")
	private int vacc_id;
	@Column(name = "citizen_id")
	private int citizen_id;
	@Column(name = "citizen_firstname")
	private String citizen_firstname;
	@Column(name = "citizen_lastname")
	private String citizen_lastname;
	@Column(name = "citizen_mobilenum")
	private long citizen_mobileno;
	@Column(name = "citizen_city")
	private String city;
	@Column(name = "citizen_gender")
	private String gender;
	@Column(name = "citizen_dob")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dob;
	@Column(name = "regis_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date regisDate;
	@Column(name = "citizen_email")
	private String citizen_email;
	@Id
	@Column(name = "citizen_aadharnum")
	private long citizen_aadharnum;
	@Column(name = "vaccine_name", table = "vaccine_details")
	private String vacc_name = "";

	@Column(name = "vaccine_date1", table = "vaccine_details")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date vacc_d1;
	@Column(name = "vaccine_date2", table = "vaccine_details")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date vacc_d2;
	@Column(name = "boostervacc_date", table = "vaccine_details")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date boostervacc_date;
	@Column(name = "vaccine_status", table = "vaccine_details")
	private String vacc_status;
	@Column(name = "vaccine_noofdoses", table = "vaccine_details")
	private Integer vacc_noofdoses;

	public long getCitizen_mobileno() {
		return citizen_mobileno;
	}

	public void setCitizen_mobileno(long citizen_mobileno) {
		this.citizen_mobileno = citizen_mobileno;
	}

	public int getVacc_id() {
		return vacc_id;
	}

	public void setVacc_id(int vacc_id) {
		this.vacc_id = vacc_id;
	}

	public int getCitizen_id() {
		return citizen_id;
	}

	public void setCitizen_id(int citizen_id) {
		this.citizen_id = citizen_id;
	}

	public String getCitizen_firstname() {
		return citizen_firstname;
	}

	public void setCitizen_firstname(String citizen_firstname) {
		this.citizen_firstname = citizen_firstname;
	}

	public String getCitizen_lastname() {
		return citizen_lastname;
	}

	public void setCitizen_lastname(String citizen_lastname) {
		this.citizen_lastname = citizen_lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getRegisDate() {
		return regisDate;
	}

	public void setRegisDate(Date regis_date) {
		this.regisDate = regis_date;
	}

	public String getCitizen_email() {
		return citizen_email;
	}

	public void setCitizen_email(String citizen_email) {
		this.citizen_email = citizen_email;
	}

	public Long getCitizen_aadharnum() {
		return citizen_aadharnum;
	}

	public void setCitizen_aadharnum(Long citizen_aadharnum) {
		this.citizen_aadharnum = citizen_aadharnum;
	}

	public String getVacc_name() {
		return vacc_name;
	}

	public void setVacc_name(String vacc_name) {
		this.vacc_name = vacc_name;
	}

	public Date getVacc_d1() {
		return vacc_d1;
	}

	public void setVacc_d1(Date vacc_d1) {
		this.vacc_d1 = vacc_d1;
	}

	public Date getVacc_d2() {
		return vacc_d2;
	}

	public void setVacc_d2(Date vacc_d2) {
		this.vacc_d2 = vacc_d2;
	}

	public Date getBoostervacc_date() {
		return boostervacc_date;
	}

	public void setBoostervacc_date(Date boostervacc_date) {
		this.boostervacc_date = boostervacc_date;
	}

	public String getVacc_status() {
		return vacc_status;
	}

	public void setVacc_status(String vacc_status) {
		this.vacc_status = vacc_status;
	}

	public Integer getVacc_noofdoses() {
		return vacc_noofdoses;
	}

	public void setVacc_noofdoses(Integer vacc_noofdoses) {
		this.vacc_noofdoses = vacc_noofdoses;
	}

	public Vaccination() {
		super();
	}

	public Vaccination(int vacc_id, int citizen_id, String citizen_firstname, String citizen_lastname,
			long citizen_mobileno, String city, String gender, Date dob, Date regisDate, String citizen_email,
			Long citizen_aadharnum, String vacc_name, Date vacc_d1, Date vacc_d2, Date boostervacc_date,
			String vacc_status, Integer vacc_noofdoses) {
		super();
		this.vacc_id = vacc_id;
		this.citizen_id = citizen_id;
		this.citizen_firstname = citizen_firstname;
		this.citizen_lastname = citizen_lastname;
		this.citizen_mobileno = citizen_mobileno;
		this.city = city;
		this.gender = gender;
		this.dob = dob;
		this.regisDate = regisDate;
		this.citizen_email = citizen_email;
		this.citizen_aadharnum = citizen_aadharnum;

		this.vacc_name = vacc_name;

		this.vacc_d1 = vacc_d1;
		this.vacc_d2 = vacc_d2;
		this.boostervacc_date = boostervacc_date;
		this.vacc_status = vacc_status;
		this.vacc_noofdoses = vacc_noofdoses;
	}

	@Override
	public String toString() {
		return "Vaccination [vacc_id=" + vacc_id + ", citizen_id=" + citizen_id + ", citizen_firstname="
				+ citizen_firstname + ", citizen_lastname=" + citizen_lastname + ", citizen_mobileno="
				+ citizen_mobileno + ", city=" + city + ", gender=" + gender + ", dob=" + dob + ", regis_date="
				+ regisDate + ", citizen_email=" + citizen_email + ", citizen_aadharnum=" + citizen_aadharnum
				+ ", fcitizen_aadharnum=" + ", vacc_name1=" + vacc_name + ", vacc_name2=" + ", boostervacc_name="
				+ ", vacc_d1=" + vacc_d1 + ", vacc_d2=" + vacc_d2 + ", boostervacc_d2=" + boostervacc_date
				+ ", vacc_status=" + vacc_status + ", vacc_noofdoses=" + vacc_noofdoses + "]";
	}

}
