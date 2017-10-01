package com.malex.model.entity;

import com.malex.model.entity.templ.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Setter
	@Getter
	@Column(name = "fullName")
	private String fullName;

	@Setter
	@Getter
	@Column(name = "email", unique = true)
	private String email;

	@Setter
	@Getter
	@Column(name = "password")
	private String password;

	@Setter
	@Getter
	@Column(name = "language")
	private String language;

	@Setter
	@Getter
	@Column(name = "occupation")
	private String occupation;

	@Setter
	@Getter
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	@Setter
	@Getter
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfRegistration;

}