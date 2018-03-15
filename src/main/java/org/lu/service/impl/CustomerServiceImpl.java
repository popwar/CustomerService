package org.lu.service.impl;

import java.time.Instant;
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
		CustomerProfileVo vo = new CustomerProfileVo();
		vo.setCustomerProfileId(po.getCustomerProfileId());
		vo.setFirstName(po.getFirstName());
		vo.setLastName(po.getLastName());
		vo.setBirthDay(po.getBirthDay().toString());

		final CustomerAddressEntity address = po.getAddress();
		vo.setHomeAddress(address.getHomeAddress());
		vo.setOfficeAddress(address.getOfficeAddress());
		vo.setEmailAddress(address.getEmailAddress());
		return vo;
	}

	public CustomerProfileVo saveCustomerProfile(
			CustomerProfileOperateVo customerProfileCreateVo) {
		CustomerProfileEntity customerProfile = new CustomerProfileEntity();
		customerProfile.setFirstName(customerProfileCreateVo.getFirstName());
		customerProfile.setLastName(customerProfileCreateVo.getLastName());

		customerProfile.setBirthDay(Date.from(LocalDate
				.of(customerProfileCreateVo.getYearOfBirth(),
						customerProfileCreateVo.getMonthOfBirth(),
						customerProfileCreateVo.getDayOfBirth()).atStartOfDay()
				.toInstant(ZoneOffset.UTC)));

		CustomerAddressEntity address = new CustomerAddressEntity();
		address.setCustomerProfile(customerProfile);
		address.setHomeAddress(customerProfileCreateVo.getHomeAddress());
		address.setOfficeAddress(customerProfileCreateVo.getOfficeAddress());
		address.setEmailAddress(customerProfileCreateVo.getEmailAddress());

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
				.of(customerProfileCreateVo.getYearOfBirth(),
						customerProfileCreateVo.getMonthOfBirth(),
						customerProfileCreateVo.getDayOfBirth()).atStartOfDay()
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
		// TODO add custom exception
		return customerProfileRepository.findByCustomerProfileId(
				customerProfileId).orElseThrow(
				() -> new RuntimeException("Customer does not exists "));
	}

}
