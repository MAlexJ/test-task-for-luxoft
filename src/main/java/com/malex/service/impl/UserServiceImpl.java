package com.malex.service.impl;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserDTO;
import com.malex.repository.UserDao;
import com.malex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public long createNewUsers(UserDTO user) throws RepositoryException {

		// #1 check user
		userDao.check(user.getEmail());

		user.setDateOfRegistration(new Date());

		// #2 save user
		return userDao.save(user);

	}

	@Override
	public List<UserDTO> getAllUsers(String fullName) {
		return userDao.getAllUsers(fullName);
	}

	@Override
	public UserDTO login(String email, String password) throws RepositoryException {

		// #1 check email
		userDao.checkEmailInDB(email);

		// #2 check password
		return userDao.getUserWithPassword(email, password);
	}

	@Override
	public UserDTO getUserById(long id) throws RepositoryException {
		return userDao.getUserById(id);
	}

	@Override
	public void updateUser(UserDTO userDTO) throws RepositoryException {
		userDao.updateUser(userDTO);
	}

}