package test.app.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.app.model.Narudzbina;
import test.app.service.BillService;
import test.app.service.DeliveryService;
import test.app.service.OrderService;
import test.app.web.dto.OrderDto;

@Component
public class OrderDtoToOrder implements Converter<OrderDto, Narudzbina>{
	
	@Autowired
	private DeliveryService ds;
	
	@Autowired
	private BillService bs;
	
	@Autowired
	private OrderService os;

	@Override
	public Narudzbina convert(OrderDto dto) {
		Narudzbina o;
		
		if(dto.getId() == null) {
			o = new Narudzbina();
		}else {
			o = os.getOne(dto.getId());
		}
		
		if(o != null) {
			o.setDate(getLocalDate(dto.getDate()));
			o.setAdress(dto.getAddress());
//			o.setId(dto.getId());
			o.setDescription(dto.getDescription());
			o.setNumber(dto.getNumber());
			o.setPrice(dto.getPrice());
			o.setDelivery(ds.getOne(dto.getDeliveryId()));
			if(dto.getBillId() != null) {
				o.setBill(bs.findOne(dto.getBillId()));
			}
		}
		
		System.out.println(o);
		return o;
		
	}
	
	private LocalDate getLocalDate(String datumS)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate datum = LocalDate.parse(datumS, formatter);
        System.out.println(datum);
        return  datum;
    }
	
	
	
	
}
