package com.philips.alerttocare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philips.alerttocare.exceptions.ResourceNotFoundException;
import com.philips.alerttocare.models.Occupancy;
import com.philips.alerttocare.repositories.OccupancyRepository;

@Service("occupancyService")
public class OccupancyServiceImplementation implements IService<Occupancy> {

	@Autowired
	private OccupancyRepository occupancyRepository;

	@Override
	public List<Occupancy> getAllObjects() {
		return occupancyRepository.findAll();
	}

	@Override
	public Occupancy getObjectById(long id) throws ResourceNotFoundException {
		Occupancy occupancy = occupancyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Occupancy record not found for this id ::" + id));
		return occupancy;
	}

	@Override
	public Occupancy saveObject(Occupancy occupancy) {
		return occupancyRepository.save(occupancy);
	}

	@Override
	public void deleteObject(Occupancy occupancy) {
		occupancyRepository.delete(occupancy);
	}

}
