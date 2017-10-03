package com.malex.service.impl;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserHistoryDTO;
import com.malex.repository.UserHistoryDao;
import com.malex.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHistoryServiceImpl implements UserHistoryService {

	@Autowired
	private UserHistoryDao userHistoryDao;

	@Override
	public UserHistoryDTO getStatusById(long id) throws RepositoryException {
		return userHistoryDao.getStatusById(id);
	}

	@Override
	public UserHistoryDTO changeStatus(boolean status, Long userId) throws RepositoryException {
		return userHistoryDao.changeStatus(status, userId);
	}
}
