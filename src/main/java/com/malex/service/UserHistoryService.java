package com.malex.service;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserHistoryDTO;

public interface UserHistoryService {

	UserHistoryDTO getStatusById(long id) throws RepositoryException;

	UserHistoryDTO changeStatus(boolean status, Long userId)throws RepositoryException;
}