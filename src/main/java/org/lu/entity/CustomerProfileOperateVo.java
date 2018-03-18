package org.lu.entity;

import javax.validation.constraints.Pattern;

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

	@NotBlank
	@Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Data format is yyyy/MM/dd")
	private String birthDay;

	@NotBlank
	private String homeAddress;

	@NotBlank
	private String officeAddress;

	@NotBlank
	@Email(message = "Please provide a valid email address")
	private String emailAddress;

}
