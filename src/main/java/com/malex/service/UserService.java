package com.malex.service;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserDTO;

import java.util.List;

public interface UserService {

	long createNewUsers(UserDTO user) throws RepositoryException;

	List<UserDTO> getAllUsers(String fullName);

	UserDTO login(String email, String password) throws RepositoryException;

	UserDTO getUserById(long id) throws RepositoryException;

	void updateUser(UserDTO userDTO) throws RepositoryException;
}