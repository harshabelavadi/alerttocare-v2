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
@Table(name = "HealthStatus")
/**
 * This HealthStatus class is an entity with 
 * id , and keeps track of patient vital science
 * and Occupancy , monitor object with their id's as foreign key 
 **/
public class HealthStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id", updatable = false)
	private Long id;
	
	@Column(name = "Heart Rate")
	private Double heartrate;
	
	@Column(name = "BP Systolic")
	private Double bpSystolic;

	@Column(name = "BP Diastolic")
	private Double bpDiastolic;
	
	@Column(name = "SPO2")
	private Double spo2;
	
	@Column(name = "Respiratory Rate")
	private Double respiratoryrate;
	
	@Column(name = "alert")
	private boolean alert;
	
	@Column(name = "Created At")
	private Date createdAt;
	
	@ManyToOne
    @JoinColumn(referencedColumnName="Id")
	private Occupancy occupancy;

	@ManyToOne
    @JoinColumn(referencedColumnName="Id")
	private HealthMonitor monitor;
	
	public HealthStatus() {
		super();
	}

	public HealthStatus(long id, Double heartrate, Double bpSystolic, Double bpDiastolic, Double spo2, Double respiratoryrate, boolean alert, Occupancy occupancy,
			HealthMonitor monitor) {
		super();
		this.id = id;
		this.heartrate = heartrate;
		this.bpSystolic = bpSystolic;
		this.bpDiastolic = bpDiastolic;
		this.spo2 = spo2;
		this.respiratoryrate = respiratoryrate;
		this.alert = alert;
		this.occupancy = occupancy;
		this.monitor = monitor;
	}

	public Long getId() {
		return id;
	}

	public Double getHeartrate() {
		return heartrate;
	}

	public void setHeartrate(Double heartrate) {
		this.heartrate = heartrate;
	}

	public Double getBpSystolic() {
		return bpSystolic;
	}

	public void setBpSystolic(Double bpSystolic) {
		this.bpSystolic = bpSystolic;
	}

	public Double getBpDiastolic() {
		return bpDiastolic;
	}

	public void setBpDiastolic(Double bpDiastolic) {
		this.bpDiastolic = bpDiastolic;
	}

	public Double getSpo2() {
		return spo2;
	}

	public void setSpo2(Double spo2) {
		this.spo2 = spo2;
	}

	public Double getRespiratoryrate() {
		return respiratoryrate;
	}

	public void setRespiratoryrate(Double respiratoryrate) {
		this.respiratoryrate = respiratoryrate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	public void setCreatedAt() {
		this.createdAt = new Date();
	}
	
	public Occupancy getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(Occupancy occupancy) {
		this.occupancy = occupancy;
	}

	public HealthMonitor getMonitor() {
		return monitor;
	}

	public void setMonitor(HealthMonitor monitor) {
		this.monitor = monitor;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}
}
