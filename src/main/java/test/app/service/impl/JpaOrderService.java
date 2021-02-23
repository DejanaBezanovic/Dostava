package test.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import test.app.model.Narudzbina;
import test.app.repository.OrderRepository;
import test.app.service.OrderService;

@Service
public class JpaOrderService implements OrderService{
	
	@Autowired
	private OrderRepository r;

	@Override
	public Page<Narudzbina> all(int page) {
		return r.findAll(PageRequest.of(page, 2));
	}

	@Override
	public Optional<Narudzbina> one(Long id) {
		return r.findById(id);
	}

	@Override
	public Narudzbina save(Narudzbina order) {
		return r.save(order);
	}

	@Override
	public Narudzbina update(Narudzbina order) {
		return r.save(order);

	}

	@Override
	public Narudzbina delete(Long id) {
		Optional<Narudzbina> op = r.findById(id);
		if(op.isPresent()) {
			r.deleteById(id);
			return op.get();
		}
		return null;
	}

	@Override
	public Page<Narudzbina> search(String adress, Long deliveryId, int page) {
		
		if(adress != null) {
			adress = "%" + adress + "%";
		}
		
		return r.search(adress, deliveryId, PageRequest.of(page, 2));
	}

	@Override
	public Narudzbina getOne(Long id) {
		return r.getOne(id);
	}
	
	
	

}
