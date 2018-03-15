package org.lu.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_address")
public class CustomerAddressEntity extends AbstractEntity {

	private static final long serialVersionUID = 3265530488276356219L;

	public CustomerAddressEntity() {
	};

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Long addressId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerProfileEntity customerProfile;

	@Column(name = "home_address")
	private String homeAddress;

	@Column(name = "office_address")
	private String officeAddress;

	@Column(name = "email_address")
	private String emailAddress;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public CustomerProfileEntity getCustomerProfile() {
		return customerProfile;
	}

	public void setCustomerProfile(CustomerProfileEntity customerProfile) {
		this.customerProfile = customerProfile;
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
