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
	public void createNewUsers(UserDTO user) throws RepositoryException {

		// #1 check user
		userDao.check(user.getEmail());

		user.setDateOfBirth(new Date());

		user.setDateOfRegistration(new Date());

		// #2 save user
		userDao.save(user);

	}

	@Override
	public List<UserDTO> getAllUsers(String fullName) {
		return userDao.getAllUsers(fullName);
	}

}