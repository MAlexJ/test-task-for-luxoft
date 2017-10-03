package com.malex.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserHistoryDTO {

	@Setter
	@Getter
	private Long id;

	@Setter
	@Getter
	private Long userId;

	@Setter
	@Getter
	private boolean status;

	@Setter
	@Getter
	private Date date;

	@Setter
	@Getter
	private String lastDate;

}