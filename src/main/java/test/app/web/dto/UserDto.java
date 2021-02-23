package test.app.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserDto {
	private Long id;
	@NotBlank
	private String username;
	@NotEmpty
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
