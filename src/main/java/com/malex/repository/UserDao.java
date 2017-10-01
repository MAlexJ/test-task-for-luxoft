package com.malex.repository;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserDTO;

import java.util.List;

public interface UserDao {

	void save(UserDTO user) throws RepositoryException;

	boolean check(String email) throws RepositoryException;

	List<UserDTO> getAllUsers(String fullName);
}