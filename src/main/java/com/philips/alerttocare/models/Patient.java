package com.philips.alerttocare.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "Patients")
/**
 * This Patient class is an entity with 
 * id , patient personal details
 * and StaffDetails object with its id as foreign key 
 * to keep track of under whom the patient is 
 * monitored
 **/

public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id", updatable = false)
	private Long id;
	
	@Column(name = "Patient Name")
	private String name;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Age")
	private int age;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Contact")
	private String contact;
	
	@Column(name = "Created At")
	private Date createdAt;
	
	@ManyToOne
    @JoinColumn(referencedColumnName="Id")
	private StaffDetails staffdetails;
	
	public Patient() {}

	public Patient(long id, String name, String address, int age, String gender, String contact, 
			StaffDetails staffdetails) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.age = age;
		this.gender = gender;
		this.contact = contact;
		this.staffdetails = staffdetails;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	public void setCreatedAt() {
		this.createdAt = new Date();
	}

	public StaffDetails getStaffdetails() {
		return staffdetails;
	}

	public void setStaffdetails(StaffDetails staffdetails) {
		this.staffdetails = staffdetails;
	}
	
}
