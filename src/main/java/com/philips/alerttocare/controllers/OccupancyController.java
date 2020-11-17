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
import com.philips.alerttocare.models.Occupancy;
import com.philips.alerttocare.services.OccupancyServiceImplementation;

@RestController
@RequestMapping("/api/alerttocare/")
public class OccupancyController {
	
	@Autowired
	private OccupancyServiceImplementation occupancyService;
	
	// get occupancies
	@GetMapping("occupancies")
	public List<Occupancy> getAllOccupancies() {
		return this.occupancyService.getAllObjects();
	}
	
	// get occupancy by id
	@GetMapping("occupancies/{id}")
	public ResponseEntity<Occupancy> getOccupancyById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
		return ResponseEntity.ok().body(this.occupancyService.getObjectById(id));
	}
	

	// save occupancy record
	@PostMapping("occupancies")
	public Occupancy createOccupancies(@RequestBody Occupancy occupancy) {
		return this.occupancyService.saveObject(occupancy);
	}
	
	// update occupancy record
	@PutMapping("occupancies/{id}")
	public ResponseEntity<Occupancy> updateOccupancy(@PathVariable(value = "id") Long id, 
			@Valid @RequestBody Occupancy occupancyrecord) throws ResourceNotFoundException {
		Occupancy occupancy = this.occupancyService.getObjectById(id);
		
		occupancy.setDischargedAt(occupancyrecord.getDischargedAt());
		occupancy.setIcu(occupancyrecord.getIcu());
		occupancy.setBed(occupancyrecord.getBed());
		occupancy.setPatient(occupancyrecord.getPatient());
		
		return ResponseEntity.ok(this.occupancyService.saveObject(occupancy));
	}
	
	// delete occupancy record
	@DeleteMapping("occupancies/{id}")
	public String deleteOccupancy(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		this.occupancyService.deleteObject(this.occupancyService.getObjectById(id));
		return "Record with id :: "+id+" is deleted";
	}
	
}
