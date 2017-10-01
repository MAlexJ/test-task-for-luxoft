package com.malex.repository.impl;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserDTO;
import com.malex.model.entity.User;
import com.malex.repository.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.malex.constants.Constant.USER_ALREADY_EXISTS_IN_THE_DATABASE;
import static com.malex.constants.Constant.USER_INSERT_ERROR;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	/**
	 * Logger
	 */
	private final static Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	@Transactional
	public void save(UserDTO user) throws RepositoryException {

		int executeUpdate = entityManager
				  .createNativeQuery("INSERT INTO users (dateofbirth, dateofregistration, email, fullname, language, occupation, password) VALUES (?,?,?,?,?,?,?)")
				  // dateofbirth
				  .setParameter(1, user.getDateOfBirth())
				  // dateofregistration
				  .setParameter(2, user.getDateOfRegistration())
				  // email
				  .setParameter(3, user.getEmail())
				  // fullname
				  .setParameter(4, user.getFullName())
				  // language
				  .setParameter(5, user.getLanguage())
				  // occupation
				  .setParameter(6, user.getOccupation())
				  // password
				  .setParameter(7, user.getPassword())
				  .executeUpdate();

		if (executeUpdate < 1) {
			LOG.error(USER_INSERT_ERROR);
			throw new RepositoryException(USER_INSERT_ERROR);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean check(String email) throws RepositoryException {

		List resultList = entityManager
				  .createNativeQuery("SELECT id FROM users WHERE email=?")
				  .setParameter(1, email)
				  .getResultList();

		if (!resultList.isEmpty()) {
			throw new RepositoryException(USER_ALREADY_EXISTS_IN_THE_DATABASE);
		}

		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getAllUsers(String fullName) {

		List<UserDTO> result = new ArrayList<>();

		List<Object[]> resultList;

		if (StringUtils.isNotBlank(fullName)) {
			resultList = entityManager
					  .createNativeQuery("SELECT id, fullname, email, dateofbirth, language, occupation FROM users WHERE fullname ILIKE ?")
					  .setParameter(1, "%" + fullName + "%")
					  .getResultList();
		} else {
			resultList = entityManager
					  .createNativeQuery("SELECT id, fullname, email, dateofbirth, language, occupation FROM users")
					  .getResultList();
		}

		if (resultList.isEmpty()) {
			return result;
		}

		for (Object[] ob : resultList) {
			UserDTO user = new UserDTO();

			// [0] id,
			user.setId(((BigInteger) ob[0]).longValue());
			// [1] fullname,
			user.setFullName((String) ob[1]);
			// [2] email,
			user.setEmail((String) ob[2]);
			// [3] dateofbirth => age
			user.setAge(30);
			// [4] 'language',
			user.setLanguage((String) ob[4]);
			// [5] occupation
			user.setOccupation((String) ob[5]);

			result.add(user);
		}

		return result;
	}

}