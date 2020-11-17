package com.philips.alerttocare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philips.alerttocare.exceptions.ResourceNotFoundException;
import com.philips.alerttocare.models.Patient;
import com.philips.alerttocare.repositories.PatientRepository;

@Service("patientService")
public class PatientServiceImplementation implements IService<Patient>{

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public List<Patient> getAllObjects() {
		return patientRepository.findAll();
	}

	@Override
	public Patient getObjectById(long id) throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient record not found for this id ::" + id));
		return patient;
	}

	@Override
	public Patient saveObject(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public void deleteObject(Patient patient) {
		patientRepository.delete(patient);
	}

}
