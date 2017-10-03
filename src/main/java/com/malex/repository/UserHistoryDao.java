package com.malex.repository;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserHistoryDTO;

public interface UserHistoryDao {

	void afterSignUp(long userId) throws RepositoryException;

	UserHistoryDTO getStatusById(long id) throws RepositoryException;

	UserHistoryDTO changeStatus(boolean status, Long userId) throws RepositoryException;
}
