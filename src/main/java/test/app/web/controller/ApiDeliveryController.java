package test.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.app.model.Delivery;
import test.app.service.DeliveryService;
import test.app.support.DeliveryToDeliveryDto;
import test.app.web.dto.DeliveryDto;

@RestController
@RequestMapping("api/dostavljaci")
public class ApiDeliveryController {
	
	@Autowired
	private DeliveryService ds;
	
	@Autowired
	private DeliveryToDeliveryDto toDto;
	
	@GetMapping
	public ResponseEntity<List<DeliveryDto>> getAll(){
		
		List<Delivery> dostavljaci = ds.all();
		return new ResponseEntity<>(toDto.convert(dostavljaci), HttpStatus.OK);
	}

}
