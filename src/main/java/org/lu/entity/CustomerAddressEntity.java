package org.lu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "customer_address")
public class CustomerAddressEntity extends AbstractEntity {

	private static final long serialVersionUID = 3265530488276356219L;

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

}
