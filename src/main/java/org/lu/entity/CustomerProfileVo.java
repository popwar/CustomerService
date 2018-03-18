package org.lu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProfileVo {

	private Long customerProfileId;

	private String firstName;

	private String lastName;

	private String birthDay;

	private String homeAddress;

	private String officeAddress;

	private String emailAddress;
}
