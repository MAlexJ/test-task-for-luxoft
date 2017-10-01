package com.malex.controller;

import com.malex.exception.RepositoryException;
import com.malex.model.dto.UserDTO;
import com.malex.model.response.UserResponse;
import com.malex.service.UserService;
import com.malex.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

			userService.createNewUsers(userDTO);

		} catch (RepositoryException ex) {

			response.setMessage(ex.getMessage());
			response.setStatus(false);
			return JsonUtil.buildGson().toJson(response);
		}

		response.setUser(userDTO);
		response.setMessage("Success");
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


	/**
	 * Check parameters
	 */
	private void checkParameters(UserDTO userDTO) {
		// Check: dateofbirth, email, fullname, language, password
	}

}