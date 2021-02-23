package test.app.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.app.model.Delivery;
import test.app.web.dto.DeliveryDto;

@Component
public class DeliveryToDeliveryDto implements Converter<Delivery,DeliveryDto>  {

	@Override
	public DeliveryDto convert(Delivery d) {
		DeliveryDto dto = new DeliveryDto();
		
		dto.setId(d.getId());
		dto.setJmbg(d.getJmbg());
		dto.setBrojLK(d.getBrojLK());
		dto.setImeIPrezime(d.getImeIPrezime());
		
		return dto;
	}
	
	public List<DeliveryDto> convert(List<Delivery> source){
		List<DeliveryDto> retVal = new ArrayList<>();
		
		for(Delivery s : source) {
			DeliveryDto dto = convert(s);
			retVal.add(dto);
		}
		
		return retVal;
	}

	
	
}
