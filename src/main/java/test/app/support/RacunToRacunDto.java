package test.app.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.app.model.Bill;
import test.app.web.dto.BillDto;

@Component
public class RacunToRacunDto implements Converter<Bill, BillDto>{

	@Override
	public BillDto convert(Bill b) {
		BillDto dto = new BillDto();
		dto.setId(b.getId());
		dto.setDate(b.getDate());
		dto.setFinalPrice(b.getFinalPrice());
		dto.setNumber(b.getNumber());
		if(b.getOrder() != null) {
		dto.setOrderId(b.getOrder().getId());
		}
		
		return dto;
	}

}
