package com.malex.model.response;

import com.malex.model.dto.UserDTO;
import com.malex.model.dto.UserHistoryDTO;
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

	@Setter
	@Getter
	private UserHistoryDTO history;

}