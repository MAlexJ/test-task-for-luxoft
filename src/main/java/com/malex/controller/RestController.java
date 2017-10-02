package com.malex.controller;

import com.malex.exception.RepositoryException;
import com.malex.exception.WebException;
import com.malex.model.dto.UserDTO;
import com.malex.model.response.UserResponse;
import com.malex.service.UserService;
import com.malex.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.malex.constants.Constant.*;

@Controller
@RequestMapping("/rest")
public class RestController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public
	@ResponseBody
	String signUp(@RequestBody UserDTO userDTO) {

		UserResponse response = new UserResponse();

		try {

			// check email and token
			checkParameters(userDTO);

		} catch (WebException ex) {
			response.setMessage(ex.getMessage());
			response.setStatus(false);
			return JsonUtil.buildGson().toJson(response);
		}

		try {

			long id = userService.createNewUsers(userDTO);
			userDTO.setId(id);

		} catch (RepositoryException ex) {
			response.setMessage(ex.getMessage());
			response.setStatus(false);
			return JsonUtil.buildGson().toJson(response);
		}

		response.setUser(userDTO);
		response.setMessage(USER_SUCCESSFULLY_CREATED);
		response.setStatus(true);
		return JsonUtil.buildGson().toJson(response);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public
	@ResponseBody
	String update(@RequestBody UserDTO userDTO) {

		UserResponse response = new UserResponse();

		try {

			// check email and token
			checkUpdateParameters(userDTO);

		} catch (WebException ex) {
			response.setMessage(ex.getMessage());
			response.setStatus(false);
			return JsonUtil.buildGson().toJson(response);
		}

		try {

			userService.updateUser(userDTO);

		} catch (RepositoryException ex) {
			response.setMessage(ex.getMessage());
			response.setStatus(false);
			return JsonUtil.buildGson().toJson(response);
		}

		response.setMessage(USER_SUCCESSFULLY_UPDATED);
		response.setStatus(true);
		return JsonUtil.buildGson().toJson(response);
	}

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public
	@ResponseBody
	String getAllUsers(@RequestParam(required = false) String fullName) {

		UserResponse response = new UserResponse();

		response.setUsers(userService.getAllUsers(fullName));
		response.setMessage("Success");
		response.setStatus(true);
		return JsonUtil.buildGson().toJson(response);
	}


	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public
	@ResponseBody
	String signIn(@RequestBody UserDTO user) {

		UserResponse response = new UserResponse();

		try {

			String email = checkEmailAndPassword(user.getEmail(), user.getPassword());

			user.setEmail(email);

		} catch (WebException ex) {
			response.setMessage(ex.getMessage());
			response.setStatus(false);
			return JsonUtil.buildGson().toJson(response);
		}

		UserDTO userDTO;

		try {

			userDTO = userService.login(user.getEmail(), user.getPassword());

		} catch (RepositoryException ex) {
			response.setMessage(ex.getMessage());
			response.setStatus(false);
			return JsonUtil.buildGson().toJson(response);
		}

		response.setUser(userDTO);
		response.setMessage(USER_IS_LOGGED);
		response.setStatus(true);
		return JsonUtil.buildGson().toJson(response);
	}

	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public
	@ResponseBody
	String getUserById(@RequestParam long id) {

		UserResponse response = new UserResponse();

		if (id < 1) {
			response.setMessage("The parameter: id should be in the request.");
			response.setStatus(false);
			return JsonUtil.buildGson().toJson(response);
		}

		UserDTO userDTO;

		try {

			userDTO = userService.getUserById(id);

		} catch (RepositoryException ex) {
			response.setMessage(ex.getMessage());
			response.setStatus(false);
			return JsonUtil.buildGson().toJson(response);
		}

		response.setUser(userDTO);
		response.setStatus(true);
		return JsonUtil.buildGson().toJson(response);
	}


	/**
	 * Check parameters
	 */
	private void checkParameters(UserDTO userDTO) throws WebException {

		// Check fullname
		if (!StringUtils.isNotBlank(userDTO.getFullName())) {
			throw new WebException("The parameter: fullname should be in the request.");
		}

		// Check email
		if (!StringUtils.isNotBlank(userDTO.getEmail())) {
			throw new WebException("The parameter: email should be in the request.");
		} else {
			userDTO.setEmail(userDTO.getEmail().trim().toLowerCase());
		}

		// Check password
		if (!StringUtils.isNotBlank(userDTO.getPassword())) {
			throw new WebException("The parameter: password should be in the request.");
		}

		// Check language
		if (!StringUtils.isNotBlank(userDTO.getLanguage())) {
			throw new WebException("The parameter: language should be in the request.");
		}

		// Check dateofbirth
		if (!StringUtils.isNotBlank(userDTO.getDateBirth())) {
			throw new WebException("The parameter: dateBirth should be in the request.");
		} else {
			System.out.println(">>>>>> userDTO.getDateBirth(): " + userDTO.getDateBirth());
		}
	}


	/**
	 * Check parameters
	 */
	private void checkUpdateParameters(UserDTO userDTO) throws WebException {

		if (userDTO.getId() < 1) {
			throw new WebException("The parameter: id should be in the request.");
		}

		// Check fullname
		if (!StringUtils.isNotBlank(userDTO.getFullName())) {
			throw new WebException("The parameter: fullname should be in the request.");
		}

		// Check email
		if (!StringUtils.isNotBlank(userDTO.getEmail())) {
			throw new WebException("The parameter: email should be in the request.");
		} else {
			userDTO.setEmail(userDTO.getEmail().trim().toLowerCase());
		}

		// Check language
		if (!StringUtils.isNotBlank(userDTO.getLanguage())) {
			throw new WebException("The parameter: language should be in the request.");
		}
	}


	/**
	 * Check parameters
	 */
	private String checkEmailAndPassword(String email, String password) throws WebException {

		// Check password
		if (!StringUtils.isNotBlank(password)) {
			throw new WebException("The parameter: password should be in the request.");
		}

		//check email
		if (!StringUtils.isNotBlank(email)) {
			throw new WebException("The parameter: email should be in the request.");
		} else {
			return email.trim().toLowerCase();
		}
	}

}