package com.philips.alerttocare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philips.alerttocare.exceptions.ResourceNotFoundException;
import com.philips.alerttocare.models.Icu;
import com.philips.alerttocare.repositories.IcuRepository;

@Service("icuService")
public class IcuServiceImplementation implements IService<Icu> {
	
	@Autowired
	private IcuRepository icuRepository;

	@Override
	public List<Icu> getAllObjects() {
		return icuRepository.findAll();
	}

	@Override
	public Icu getObjectById(long id) throws ResourceNotFoundException {
		Icu icu = icuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Icu record not found for this id ::" + id));
		return icu;
	}

	@Override
	public Icu saveObject(Icu icu) {
		return icuRepository.save(icu);
	}

	@Override
	public void deleteObject(Icu icu) {
		icuRepository.delete(icu);		
	}
}
