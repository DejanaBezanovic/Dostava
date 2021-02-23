package test.app.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.app.model.Narudzbina;
import test.app.service.OrderService;
import test.app.support.OrderDtoToOrder;
import test.app.support.OrderToOrderDto;
import test.app.web.dto.OrderDto;

@RestController
@RequestMapping(value = "/api/orders")
public class ApiOrderController {
	
	@Autowired
	private OrderService os;
	
	@Autowired
	private OrderToOrderDto toDto;
	
	@Autowired
	private OrderDtoToOrder toOrder;
	
	@PreAuthorize("hasRole('KORISNIK')")
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<OrderDto>> get(@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "deliveryId", required = false) Long deliveryId,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {

		Page<Narudzbina> page = null;

		if (address != null || deliveryId != null) {
			page = os.search(address, deliveryId, pageNo);
		} else {
			page = os.all(pageNo);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

		return new ResponseEntity<>(toDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('KORISNIK')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<OrderDto> getOne(@PathVariable Long id) {
		Optional<Narudzbina> order= os.one(id);
		if (!order.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(order.get()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('KORISNIK')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<OrderDto> delete(@PathVariable Long id) {
		Narudzbina deleted = os.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('KORISNIK')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<OrderDto> add(@Valid @RequestBody OrderDto newDto) {

		Narudzbina order = toOrder.convert(newDto);
		Narudzbina saved = os.save(order);

		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('KORISNIK')")
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<OrderDto> edit(@Validated @RequestBody OrderDto dto, @PathVariable Long id) {

		if (!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Narudzbina order = toOrder.convert(dto);
		Narudzbina persisted = os.save(order);

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);
	}

}
