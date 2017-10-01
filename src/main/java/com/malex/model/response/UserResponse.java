package com.malex.model.response;

import com.malex.model.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class UserResponse extends Response {

	@Setter
	@Getter
	private UserDTO user;

	@Setter
	@Getter
	private List<UserDTO> users;

}