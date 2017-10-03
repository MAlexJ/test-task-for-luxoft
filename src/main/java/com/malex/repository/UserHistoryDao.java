package com.malex.repository;

import com.malex.exception.RepositoryException;

public interface UserHistoryDao {

	void afterSignUp(long userId) throws RepositoryException;

}
