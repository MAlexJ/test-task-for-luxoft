package com.malex.service;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserDTO;

import java.util.List;

public interface UserService {

	void createNewUsers(UserDTO user) throws RepositoryException;

	List<UserDTO> getAllUsers(String fullName);

}
