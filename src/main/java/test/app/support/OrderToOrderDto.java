package test.app.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.app.model.Narudzbina;
import test.app.web.dto.OrderDto;

@Component
public class OrderToOrderDto implements Converter<Narudzbina, OrderDto> {
	
	
	@Override
	public OrderDto convert(Narudzbina s) {
		
		OrderDto dto = new OrderDto();
		
		dto.setAddress(s.getAdress());
		dto.setDate(getString(s.getDate()));
		dto.setDescription(s.getDescription());
		dto.setId(s.getId());
		dto.setNumber(s.getNumber());
		dto.setPrice(s.getPrice());
		dto.setDeliveryId(s.getDelivery().getId());
		dto.setDeliveryName(s.getDelivery().getImeIPrezime());
		if(s.getBill() != null) {
			dto.setBillId(s.getBill().getId());
		}
		
		return dto;
	} 
	
	public List<OrderDto> convert(List<Narudzbina> source){
		List<OrderDto> ret = new ArrayList<>();
		
		for(Narudzbina z : source){
			ret.add(convert(z));
		}
		
		return ret;
	}
	
	private String getString(LocalDate datum)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatiran = formatter.format(datum);
        return  formatiran;
    }

}
