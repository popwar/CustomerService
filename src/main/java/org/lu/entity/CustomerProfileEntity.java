package org.lu.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer_profile")
public class CustomerProfileEntity extends AbstractEntity {

	private static final long serialVersionUID = 4488572676028523311L;

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
}
