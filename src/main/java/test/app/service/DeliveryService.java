package test.app.service;

import java.util.List;
import java.util.Optional;

import test.app.model.Delivery;

public interface DeliveryService {
	
	Optional<Delivery> one(Long id);
	
	List<Delivery> all();
	

	Delivery getOne(Long id);
}
