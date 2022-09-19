package org.sbf.neednet.commons.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private int userNumber;
	private String name;
	private String email;
	private String place;
	private int age;
	private String gender;
	private String occupation;
	private int mobileNumber;
}
