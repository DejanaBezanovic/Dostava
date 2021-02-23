package test.app.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.app.model.User;
import test.app.web.dto.UserDto;

;


@Component
public class UserToUserDto implements Converter<User, UserDto>{

	@Override
	public UserDto convert(User source) {
		UserDto target = new UserDto();
		
		target.setId(source.getId());
		target.setUsername(source.getUsername());
		
		return target;
	}

	
}
