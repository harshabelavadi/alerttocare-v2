package com.philips.alerttocare.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.philips.alerttocare.exceptions.ResourceNotFoundException;
import com.philips.alerttocare.models.HealthStatus;
import com.philips.alerttocare.services.HealthStatusServiceImplementation;

@RestController
@RequestMapping("/api/alerttocare/")
public class HealthStatusController {
	
	@Autowired
	private HealthStatusServiceImplementation statusService;
	
	// get status
	@GetMapping("healthstatus")
	public List<HealthStatus> getAllStatus() {
		return this.statusService.getAllObjects();
	}
	
	// get status by id
	@GetMapping("healthstatus/{id}")
	public ResponseEntity<HealthStatus> getStatusById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		return ResponseEntity.ok().body(this.statusService.getObjectById(id));
	}

	// save status record
	@PostMapping("healthstatus")
	public HealthStatus createStatus(@RequestBody HealthStatus healthstatus) {
		return this.statusService.saveObject(healthstatus);
	}
	
	// update status record
	@PutMapping("healthstatus/{id}")
	public ResponseEntity<HealthStatus> updateStatus(@PathVariable(value = "id") Long id, 
			@Valid @RequestBody HealthStatus healthstatusRecord) throws ResourceNotFoundException {
		HealthStatus healthstatus = this.statusService.getObjectById(id);
		
		healthstatus.setBpDiastolic(healthstatusRecord.getBpDiastolic());
		healthstatus.setBpSystolic(healthstatusRecord.getBpSystolic());
		healthstatus.setHeartrate(healthstatusRecord.getHeartrate());
		healthstatus.setRespiratoryrate(healthstatusRecord.getRespiratoryrate());
		healthstatus.setSpo2(healthstatusRecord.getSpo2());
		healthstatus.setMonitor(healthstatusRecord.getMonitor());
		healthstatus.setOccupancy(healthstatusRecord.getOccupancy());
		
		return ResponseEntity.ok(this.statusService.saveObject(healthstatus));
	}
	
	// delete status record
	@DeleteMapping("healthstatus/{id}")
	public String deleteStatus(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		this.statusService.deleteObject(this.statusService.getObjectById(id));
		return "Record with id :: "+id+" is deleted";
	}
	
}
