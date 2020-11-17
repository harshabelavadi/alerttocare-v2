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
		Double bpSystolic = healthstatus.getBpSystolic();
		Double bpDiastolic = healthstatus.getBpDiastolic();
		Double heartRate = healthstatus.getHeartrate();
		Double respRate = healthstatus.getRespiratoryrate();
		Double spo2Rate = healthstatus.getSpo2();
		
		boolean bpSystolicInRange = (bpSystolic >= 90 && bpSystolic < 120); 
		boolean bpDiastolicInRange = (bpDiastolic >= 60 && bpDiastolic < 80); 
		boolean heartRateInRange = (heartRate >= 60 && heartRate <= 100);
		boolean respRateInRange = (respRate >= 12 && respRate <= 18);
		boolean spo2InRange = (spo2Rate >= 95);
		return (bpSystolicInRange && bpDiastolicInRange
				&& heartRateInRange && respRateInRange
				&& spo2InRange);
	}

}
