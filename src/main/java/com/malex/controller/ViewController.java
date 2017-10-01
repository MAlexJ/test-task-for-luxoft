package com.malex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String get() {
		return "index";
	}

	/**
	 * @return analytics page
	 */
	@RequestMapping(path = "/analytics", method = RequestMethod.GET)
	public String home() {
		return "views/analytics";
	}

	/**
	 * @return users page
	 */
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public String articles() {
		return "views/users";
	}

	/**
	 * @return profile page
	 */
	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	public String profile() {
		return "views/profile";
	}

	/**
	 * @return status page
	 */
	@RequestMapping(path = "/status", method = RequestMethod.GET)
	public String status() {
		return "views/status";
	}

	/**
	 * @return info page
	 */
	@RequestMapping(path = "/info", method = RequestMethod.GET)
	public String info() {
		return "views/info";
	}

	/**
	 * @return signIn page
	 */
	@RequestMapping(path = "/signin", method = RequestMethod.GET)
	public String signIn() {
		return "views/signin";
	}

	/**
	 * @return signUp page
	 */
	@RequestMapping(path = "/signup", method = RequestMethod.GET)
	public String signUp() {
		return "views/signup";
	}

}