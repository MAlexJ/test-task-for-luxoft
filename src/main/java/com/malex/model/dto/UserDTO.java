package com.malex.model.dto;

import com.malex.util.InvisibleJson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
public class UserDTO {

	@Setter
	@Getter
	private long id;

	@Setter
	@Getter
	private String fullName;

	@Setter
	@Getter
	private String email;

	@Setter
	@Getter
	private String language;

	@Setter
	@Getter
	private String occupation;

	@Setter
	@Getter
	private int age;

	@Setter
	@Getter
	@InvisibleJson
	private Date dateOfBirth;

	@Setter
	@Getter
	@InvisibleJson
	private String dateBirth;

	@Setter
	@Getter
	@InvisibleJson
	private Date dateOfRegistration;

	@Setter
	@Getter
	@InvisibleJson
	private String password;

}