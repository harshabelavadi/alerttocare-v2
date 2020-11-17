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
@Table(name = "Occupancies")
/**
 * This Occupancy class is an entity with 
 * id , time stamp of discharge date
 * and Icu , Bed , Patient object with their id's as foreign key 
 * to keep track of patient admitting and discharged details
 **/
public class Occupancy implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id", updatable = false)
	private Long id;
	
	@Column(name = "Occupied At")
	private Date occupiedAt;
	
	@Column(name = "Discharged At")
	private Date dischargedAt;	
	
	@ManyToOne
	@JoinColumn(referencedColumnName="Id")
	private Icu icu;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="Id")
	private Bed bed;
	
	@ManyToOne
    @JoinColumn(referencedColumnName="Id")
	private Patient patient;
	
	public Occupancy() {}
	
	public Occupancy(long id, Date dischargedAt, Bed bed, Patient patient, Icu icu) {
		this.id = id;
		this.dischargedAt = dischargedAt;
		this.bed = bed;
		this.patient = patient;
		this.icu = icu;
	}

	public Long getId() {
		return id;
	}

	public Date getOccupiedAt() {
		return occupiedAt;
	}
	
	@PrePersist
	public void setOccupiedAt() {
		this.occupiedAt = new Date();
	}

	public Date getDischargedAt() {
		return dischargedAt;
	}

	public void setDischargedAt(Date dischargedAt) {
		this.dischargedAt = dischargedAt;
	}

	public Bed getBed() {
		return bed;
	}

	public void setBed(Bed bed) {
		this.bed = bed;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Icu getIcu() {
		return icu;
	}

	public void setIcu(Icu icu) {
		this.icu = icu;
	}
		
}
