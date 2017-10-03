package com.malex.repository.impl;

import com.malex.exception.RepositoryException;
import com.malex.model.entity.UserHistory;
import com.malex.repository.UserHistoryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.malex.constants.Constant.USER_SAVING_USER_HISTORY;

@Repository
public class UserHistoryDaoImpl extends BaseDao<UserHistory> implements UserHistoryDao {

	/**
	 * Logger
	 */
	private final static Logger LOG = LoggerFactory.getLogger(UserHistoryDaoImpl.class);

	@Override
	@Transactional
	public void afterSignUp(long userId) throws RepositoryException {

		int executeUpdate = entityManager
				  .createNativeQuery("INSERT INTO users_history (userid, status, date) VALUES (?,?,?)")
				  .setParameter(1, userId)
				  .setParameter(2, true)
				  .setParameter(3, new Date())
				  .executeUpdate();

		if (executeUpdate < 1) {
			LOG.error(USER_SAVING_USER_HISTORY);
			throw new RepositoryException(USER_SAVING_USER_HISTORY);
		}

	}

}