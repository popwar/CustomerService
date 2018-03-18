package org.lu.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerProfileOperateVo {

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@Digits(integer = 2, fraction = 0)
	@Min(value = 1)
	@Max(value = 31)
	private int dayOfBirth;

	@Digits(integer = 2, fraction = 0)
	@Min(value = 1)
	@Max(value = 12)
	private int monthOfBirth;

	@Digits(integer = 4, fraction = 0)
	private int yearOfBirth;

	@NotBlank
	private String homeAddress;

	@NotBlank
	private String officeAddress;

	@NotBlank
	@Email(message = "Please provide a valid email address")
	private String emailAddress;

}
