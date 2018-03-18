package org.lu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CustomerProfileVo {

	final private Long customerProfileId;

	final private String firstName;

	final private String lastName;

	final private String birthDay;

	final private String homeAddress;

	final private String officeAddress;

	final private String emailAddress;
}
