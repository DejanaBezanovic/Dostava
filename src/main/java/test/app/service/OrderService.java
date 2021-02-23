package test.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import test.app.model.Narudzbina;

public interface OrderService {
	
	Page<Narudzbina> all(int pageNo);
	
	Optional<Narudzbina> one(Long id);
	
	Narudzbina getOne(Long id);
	
	Narudzbina save(Narudzbina order);
	
	Narudzbina update(Narudzbina order);
	
	Narudzbina delete(Long id);
	
	Page<Narudzbina> search(String adress, Long deliveryId, int pageNum);


}
