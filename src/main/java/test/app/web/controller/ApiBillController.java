package test.app.web.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.app.model.Bill;
import test.app.model.Narudzbina;
import test.app.service.BillService;
import test.app.service.OrderService;
import test.app.support.RacunToRacunDto;
import test.app.web.dto.BillDto;

@RestController
@RequestMapping("api/racuni")
public class ApiBillController {
	
	@Autowired
	private BillService bs;
	
	@Autowired
	private OrderService os;
	
	@Autowired
	private RacunToRacunDto toDto;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<BillDto> getOne(@PathVariable Long id) {
		Optional<Bill> b= bs.one(id);
		if (!b.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(b.get()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<BillDto> add(@PathVariable Long id) {
		
		Narudzbina narudzbina = os.getOne(id);
		Bill bill = new Bill();
		bill.setNumber(narudzbina.getNumber());
		bill.setFinalPrice(narudzbina.getPrice());
		bill.setDate(LocalDate.now());
		bill.setOrder(narudzbina);
		
		
		Bill saved = bs.save(bill);
		narudzbina.setBill(saved);
		os.save(narudzbina);

		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
	

}
