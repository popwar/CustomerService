package org.lu.service.impl;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.lu.entity.CustomerAddressEntity;
import org.lu.entity.CustomerProfileEntity;
import org.lu.entity.CustomerProfileOperateVo;
import org.lu.entity.CustomerProfileVo;
import org.lu.repository.CustomerProfileRepository;
import org.lu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.lu.exception.CustomerException;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerProfileRepository customerProfileRepository;

	@Override
	public List<CustomerProfileVo> findCustomerProfile() {

		return customerProfileRepository.findAll().stream()
				.map(profile -> convertToProfileVo(profile))
				.collect(Collectors.toList());
	}

	private CustomerProfileVo convertToProfileVo(CustomerProfileEntity po) {

		CustomerAddressEntity address = po.getAddress();

		CustomerProfileVo vo = CustomerProfileVo.builder()
				.customerProfileId(po.getCustomerProfileId())
				.firstName(po.getFirstName()).lastName(po.getFirstName())
				.birthDay(po.getBirthDay().toString())
				.homeAddress(address.getHomeAddress())
				.officeAddress(address.getOfficeAddress())
				.emailAddress(address.getEmailAddress()).build();

		return vo;
	}

	@Override
	public CustomerProfileVo saveCustomerProfile(
			CustomerProfileOperateVo customerProfileCreateVo) {

		Date birthDay = Date.from(LocalDate
				.parse(customerProfileCreateVo.getBirthDay()).atStartOfDay()
				.toInstant(ZoneOffset.UTC));

		CustomerProfileEntity customerProfile = CustomerProfileEntity.builder()
				.firstName(customerProfileCreateVo.getFirstName())
				.lastName(customerProfileCreateVo.getLastName())
				.birthDay(birthDay).build();

		CustomerAddressEntity address = CustomerAddressEntity.builder()
				.customerProfile(customerProfile)
				.homeAddress(customerProfileCreateVo.getHomeAddress())
				.officeAddress(customerProfileCreateVo.getOfficeAddress())
				.emailAddress(customerProfileCreateVo.getEmailAddress())
				.build();

		customerProfile.setAddress(address);
		return this.convertToProfileVo(customerProfileRepository
				.save(customerProfile));

	}

	@Override
	public CustomerProfileVo updateCustomerProfile(long customerId,
			CustomerProfileOperateVo customerProfileCreateVo) {
		CustomerProfileEntity customerProfile = this
				.getCustomerProfile(customerId);
		customerProfile.setFirstName(customerProfileCreateVo.getFirstName());
		customerProfile.setLastName(customerProfileCreateVo.getLastName());

		customerProfile.setBirthDay(Date.from(LocalDate
				.parse(customerProfileCreateVo.getBirthDay()).atStartOfDay()
				.toInstant(ZoneOffset.UTC)));

		CustomerAddressEntity address = customerProfile.getAddress();
		address.setCustomerProfile(customerProfile);
		address.setHomeAddress(customerProfileCreateVo.getHomeAddress());
		address.setOfficeAddress(customerProfileCreateVo.getOfficeAddress());
		address.setEmailAddress(customerProfileCreateVo.getEmailAddress());

		customerProfile.setAddress(address);
		return this.convertToProfileVo(customerProfileRepository
				.save(customerProfile));
	}

	@Override
	public void deleteeCustomerProfile(long customerProfileId) {
		customerProfileRepository.delete(customerProfileId);
	}

	private CustomerProfileEntity getCustomerProfile(long customerProfileId) {

		return customerProfileRepository.findByCustomerProfileId(
				customerProfileId).orElseThrow(
				() -> new CustomerException("Customer does not exists "));
	}

}
