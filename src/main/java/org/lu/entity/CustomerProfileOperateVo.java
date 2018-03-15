package org.lu.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(int dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public int getMonthOfBirth() {
		return monthOfBirth;
	}

	public void setMonthOfBirth(int monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
