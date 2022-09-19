package org.sbf.neednet.commons.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "user_number")
	private int userNumber;
	private String name;
	private String email;
	private String place;
	private int age;
	private String gender;
	private String occupation;

	private int mobileNumber;

	@OneToOne(mappedBy = "user")
	private UserTrigger trigger;

}
