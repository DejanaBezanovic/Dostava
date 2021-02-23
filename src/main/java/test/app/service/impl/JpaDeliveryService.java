package test.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.app.model.Delivery;
import test.app.repository.DeliveryRepository;
import test.app.service.DeliveryService;

@Service
public class JpaDeliveryService implements DeliveryService{
	
	@Autowired
	private DeliveryRepository r;
	
	@Override
	public Optional<Delivery> one(Long id) {
		// TODO Auto-generated method stub
		return r.findById(id);
	}

	@Override
	public List<Delivery> all() {
		// TODO Auto-generated method stub
		return r.findAll();
	}


	@Override
	public Delivery getOne(Long id) {
		// TODO Auto-generated method stub
		 return r.getOne(id);
	}


}
