package test.app.service;

import java.util.Optional;

import test.app.model.Bill;

public interface BillService {
	
	Bill findOne(Long id);
	
	Optional<Bill> one(Long id);
	
	Bill save(Bill bill);

}
