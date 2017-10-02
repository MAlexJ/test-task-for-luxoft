package com.malex.repository;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserDTO;

import java.util.List;

public interface UserDao {

	long save(UserDTO user) throws RepositoryException;

	boolean check(String email) throws RepositoryException;

	List<UserDTO> getAllUsers(String fullName);

	void checkEmailInDB(String email) throws RepositoryException;

	UserDTO getUserWithPassword(String email, String password) throws RepositoryException;

	UserDTO getUserById(long id) throws RepositoryException;

	void updateUser(UserDTO userDTO) throws RepositoryException;

}