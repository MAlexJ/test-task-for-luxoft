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

import static com.malex.constants.Constant.*;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	/**
	 * Logger
	 */
	private final static Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	@Transactional
	public long save(UserDTO user) throws RepositoryException {

		int executeUpdate = entityManager
				  .createNativeQuery("INSERT INTO users (dateofregistration, email, fullname, language, occupation, password) VALUES (?,?,?,?,?,?)")
				  // dateofregistration
				  .setParameter(1, user.getDateOfRegistration())
				  // email
				  .setParameter(2, user.getEmail())
				  // fullname
				  .setParameter(3, user.getFullName())
				  // language
				  .setParameter(4, user.getLanguage())
				  // occupation
				  .setParameter(5, user.getOccupation())
				  // password
				  .setParameter(6, user.getPassword())
				  .executeUpdate();

		if (executeUpdate < 1) {
			LOG.error(USER_INSERT_ERROR);
			throw new RepositoryException(USER_INSERT_ERROR);
		}

		List<Object[]> resultList = entityManager
				  .createNativeQuery("SELECT id, fullname FROM users WHERE email=?")
				  .setParameter(1, user.getEmail())
				  .getResultList();

		Object[] ob = resultList.get(0);
		return ((BigInteger) ob[0]).longValue();

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
					  .createNativeQuery("SELECT id, fullname, email, language, occupation FROM users WHERE fullname ILIKE ?")
					  .setParameter(1, "%" + fullName + "%")
					  .getResultList();
		} else {
			resultList = entityManager
					  .createNativeQuery("SELECT id, fullname, email, language, occupation FROM users")
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
			// [3] 'language',
			user.setLanguage((String) ob[3]);
			// [4] occupation
			user.setOccupation((String) ob[4]);

			result.add(user);
		}

		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public void checkEmailInDB(String email) throws RepositoryException {

		List resultList = entityManager
				  .createNativeQuery("SELECT id FROM users WHERE email=?")
				  .setParameter(1, email)
				  .getResultList();

		if (resultList.isEmpty()) {
			throw new RepositoryException(USER_DOES_NOT_EXIST);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO getUserWithPassword(String email, String password) throws RepositoryException {

		List<Object[]> resultList = entityManager
				  .createNativeQuery("SELECT id, fullname FROM users WHERE email=? AND password=?")
				  .setParameter(1, email)
				  .setParameter(2, password)
				  .getResultList();

		if (resultList.isEmpty()) {
			throw new RepositoryException(USER_WRONG_PASSWORD);
		}

		Object[] ob = resultList.get(0);

		UserDTO userDTO = new UserDTO();

		userDTO.setId(((BigInteger) ob[0]).longValue());
		userDTO.setFullName((String) ob[1]);

		return userDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO getUserById(long id) throws RepositoryException {

		List<Object[]> resultList = entityManager
				  .createNativeQuery("SELECT id, fullname, email, language, occupation FROM users WHERE id = ?")
				  .setParameter(1, id)
				  .getResultList();

		if (resultList.isEmpty()) {
			throw new RepositoryException(USER_DOES_NOT_EXIST);
		}

		Object[] ob = resultList.get(0);

		UserDTO user = new UserDTO();

		// [0] id,
		user.setId(((BigInteger) ob[0]).longValue());
		// [1] fullname,
		user.setFullName((String) ob[1]);
		// [2] email,
		user.setEmail((String) ob[2]);
		// [3] 'language',
		user.setLanguage((String) ob[3]);
		// [4] occupation
		user.setOccupation((String) ob[4]);

		return user;
	}

	@Override
	@Transactional
	public void updateUser(UserDTO userDTO) throws RepositoryException {

		int executeUpdate = entityManager
				  .createNativeQuery("UPDATE users SET fullname=?, email=?, language=?, occupation=? WHERE id=?")
				  .setParameter(1, userDTO.getFullName())
				  .setParameter(2, userDTO.getEmail())
				  .setParameter(3, userDTO.getLanguage())
				  .setParameter(4, userDTO.getOccupation())
				  .setParameter(5, userDTO.getId())
				  .executeUpdate();

		if (executeUpdate < 1) {
			LOG.error(USER_ERROR_UPDATED);
			throw new RepositoryException(USER_ERROR_UPDATED);
		}
	}

}