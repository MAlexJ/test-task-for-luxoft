package com.malex.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
public class Response implements Serializable {

	@Setter
	@Getter
	private boolean status;

	@Setter
	@Getter
	private String message;

}