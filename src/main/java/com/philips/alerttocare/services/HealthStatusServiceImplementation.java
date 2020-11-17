package com.philips.alerttocare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philips.alerttocare.exceptions.ResourceNotFoundException;
import com.philips.alerttocare.models.HealthStatus;
import com.philips.alerttocare.repositories.HealthStatusRepository;

@Service("statusService")
public class HealthStatusServiceImplementation implements IService<HealthStatus> {

	@Autowired
	private HealthStatusRepository statusRepository;
	
	@Override
	public List<HealthStatus> getAllObjects() {
		return statusRepository.findAll();
	}

	@Override
	public HealthStatus getObjectById(long id) throws ResourceNotFoundException {
		HealthStatus status = statusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Status record not found for this id ::" + id));
		return status;
	}

	@Override
	public HealthStatus saveObject(HealthStatus healthstatus) {
		healthstatus = this.triggerAlerts(healthstatus);
		return statusRepository.save(healthstatus);
	}

	@Override
	public void deleteObject(HealthStatus healthstatus) {
		statusRepository.delete(healthstatus);
	}
	
	private HealthStatus triggerAlerts(HealthStatus healthstatus) {		
		this.isBpSystolicInRange(healthstatus);
		this.isBpDiastolicInRange(healthstatus);
		this.isHeartRateInRange(healthstatus);
		this.isRespRateInRange(healthstatus);
		this.isSpo2InRange(healthstatus);
		
		return healthstatus;
	}

	private void isBpSystolicInRange(HealthStatus healthstatus) {
		Double bpSystolic = healthstatus.getBpSystolic();
		healthstatus.setAlert(!(bpSystolic >= 90 && bpSystolic < 120));
	}
	
	private void isBpDiastolicInRange(HealthStatus healthstatus) {
		Double bpDiastolic = healthstatus.getBpDiastolic();
		healthstatus.setAlert(healthstatus.isAlert() && !(bpDiastolic >= 60 && bpDiastolic < 80)); 
	}
	
	private void isHeartRateInRange(HealthStatus healthstatus) {
		Double heartRate = healthstatus.getHeartrate(); 
		healthstatus.setAlert(healthstatus.isAlert() && !(heartRate >= 60 && heartRate <= 100));
	}
	
	private void isRespRateInRange(HealthStatus healthstatus) {
		Double respRate = healthstatus.getRespiratoryrate(); 
		healthstatus.setAlert(healthstatus.isAlert() && !(respRate >= 12 && respRate <= 18));
	}	
	
	private void isSpo2InRange(HealthStatus healthstatus) {
		Double spo2Rate = healthstatus.getSpo2(); 
		healthstatus.setAlert(healthstatus.isAlert() &&  !(spo2Rate >= 95) );
	}
}
