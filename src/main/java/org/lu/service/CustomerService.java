package org.lu.service;

import java.util.List;

import org.lu.entity.CustomerProfileEntity;
import org.lu.entity.CustomerProfileOperateVo;
import org.lu.entity.CustomerProfileVo;

public interface CustomerService {

	List<CustomerProfileVo> findCustomerProfile();

	CustomerProfileVo saveCustomerProfile(
			CustomerProfileOperateVo customerProfileCreateVo);

	CustomerProfileVo updateCustomerProfile(long customerProfileId,
			CustomerProfileOperateVo customerProfileCreateVo);

	void deleteeCustomerProfile(long customerProfileId);
}
