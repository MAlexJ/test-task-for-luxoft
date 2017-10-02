package com.malex.exception;

public class WebException extends Exception {

	public WebException() {
	}

	public WebException(String message) {
		super(message);
	}

	public WebException(String message, Throwable cause) {
		super(message, cause);
	}

}