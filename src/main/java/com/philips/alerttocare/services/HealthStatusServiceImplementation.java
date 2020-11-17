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
		if (!isVitalsInRange(healthstatus))
			healthstatus.setAlert(true);
		else
			healthstatus.setAlert(false);
		return healthstatus;
	}

	private boolean isVitalsInRange(HealthStatus healthstatus) {

		if (!this.isBpSystolicInRange(healthstatus.getBpSystolic())) {
			return true;
		}
		
		if (!this.isBpDiastolicInRange(healthstatus.getBpDiastolic())) {
			return true;
		}
		
		if (!this.isHeartRateInRange(healthstatus.getHeartrate())) {
			return true;
		}
		
		if (!this.isRespRateInRange(healthstatus.getRespiratoryrate())) {
			return true;
		}
		
		if (!this.isSpo2InRange(healthstatus.getSpo2())) {
			return true;
		}
		
		return false;
	}
	
	private boolean isBpSystolicInRange(Double bpSystolic) {
		return (bpSystolic >= 90 && bpSystolic < 120);
	}
	
	private boolean isBpDiastolicInRange(Double bpDiastolic) {
		return (bpDiastolic >= 60 && bpDiastolic < 80); 
	}
	
	private boolean isHeartRateInRange(Double heartRate) {
		return (heartRate >= 60 && heartRate <= 100); 
	}
	
	private boolean isRespRateInRange(Double respRate) {
		return (respRate >= 12 && respRate <= 18);
	}	
	
	private boolean isSpo2InRange(Double spo2Rate) {
		return (spo2Rate >= 95);
	}
}
