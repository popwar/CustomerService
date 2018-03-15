package org.lu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer_profile")
public class CustomerProfileEntity extends AbstractEntity {

	private static final long serialVersionUID = 4488572676028523311L;

	public CustomerProfileEntity() {
	};

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_Profile_Id")
	private Long customerProfileId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private Date birthDay;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customerProfile")
	private CustomerAddressEntity address;

	public CustomerAddressEntity getAddress() {
		return address;
	}

	public void setAddress(CustomerAddressEntity address) {
		this.address = address;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Long getCustomerProfileId() {
		return customerProfileId;
	}

	public void setCustomerProfileId(Long customerProfileId) {
		this.customerProfileId = customerProfileId;
	}

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

}
