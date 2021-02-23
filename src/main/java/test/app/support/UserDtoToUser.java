package test.app.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.app.model.User;
import test.app.service.UserService;
import test.app.web.dto.UserDto;





@Component
public class UserDtoToUser implements Converter<UserDto, User> {

	@Autowired
	private UserService userService;
	
	@Override
	public User convert(UserDto source) {
		User target = null;
		if(source.getId() != null) {
			target = userService.one(source.getId()).get();
		}
		
		// čak i da je došao dto sa popunjenim ID
		// moguće je da ne postoji, pa ga onda treba kreirati
		if(target == null) { 
			target = new User();
		}
				
		target.setUsername(source.getUsername());
		
		return target;
	}

}
