package com.philips.alerttocare.services;

import java.util.List;

import com.philips.alerttocare.exceptions.ResourceNotFoundException;

public interface IService<T> {
	public List<T> getAllObjects();
	public T getObjectById(long id) throws ResourceNotFoundException;
	public T saveObject(T object);
	public void deleteObject(T object);
}
