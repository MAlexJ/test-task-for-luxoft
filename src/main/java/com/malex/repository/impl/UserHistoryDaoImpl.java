package com.malex.repository.impl;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserHistoryDTO;
import com.malex.model.entity.UserHistory;
import com.malex.repository.UserHistoryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.malex.constants.Constant.THERE_IS_NO_RECORD_IN_THE_DATABASE;
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

	@Override
	@Transactional(readOnly = true)
	public UserHistoryDTO getStatusById(long id) throws RepositoryException {

		List<Object[]> resultList = entityManager
				  .createNativeQuery("SELECT id, date, status FROM users_history WHERE userid=? ORDER BY date DESC LIMIT 1")
				  .setParameter(1, id)
				  .getResultList();

		if (resultList.isEmpty()) {
			throw new RepositoryException(THERE_IS_NO_RECORD_IN_THE_DATABASE);
		}

		Object[] ob = resultList.get(0);

		UserHistoryDTO historyDTO = new UserHistoryDTO();

		// [0] id
		historyDTO.setId(((BigInteger) ob[0]).longValue());

		// [1] date
		Timestamp dataDB = (Timestamp) ob[1];
		Date date = new Date(dataDB.getTime());
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdfDate.format(date);
		historyDTO.setLastDate(strDate);

		// [2] status
		historyDTO.setStatus((Boolean) ob[2]);

		historyDTO.setUserId(id);

		return historyDTO;

	}

	@Override
	@Transactional
	public UserHistoryDTO changeStatus(boolean status, Long userId) throws RepositoryException {

		Date date = new Date();

		int executeUpdate = entityManager
				  .createNativeQuery("INSERT INTO users_history (userid, status, date) VALUES (?,?,?)")
				  .setParameter(1, userId)
				  .setParameter(2, status)
				  .setParameter(3, date)
				  .executeUpdate();

		if (executeUpdate < 1) {
			LOG.error(USER_SAVING_USER_HISTORY);
			throw new RepositoryException(USER_SAVING_USER_HISTORY);
		}

		UserHistoryDTO historyDTO = new UserHistoryDTO();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdfDate.format(date);
		historyDTO.setLastDate(strDate);

		historyDTO.setStatus(status);

		historyDTO.setUserId(userId);

		return historyDTO;
	}

}