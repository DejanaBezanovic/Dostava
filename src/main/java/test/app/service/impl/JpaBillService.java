package test.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.app.model.Bill;
import test.app.repository.BillRepository;
import test.app.service.BillService;

@Service
public class JpaBillService implements BillService {
	
	@Autowired
	private BillRepository r;

	@Override
	public Optional<Bill> one(Long id) {
		return r.findById(id);
	}

	@Override
	public Bill save(Bill bill) {
		return r.save(bill);
	}

	@Override
	public Bill findOne(Long id) {
		return r.getOne(id);
	}
	
	
}
