package com.malex.model.entity;

import com.malex.model.entity.templ.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "users_history")
public class UserHistory extends BaseEntity {

	@Setter
	@Getter
	@Column(name = "userId")
	private Long userId;

	@Setter
	@Getter
	@Column(name = "status")
	private boolean status;

	@Setter
	@Getter
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

}