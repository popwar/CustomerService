package org.lu.web;

import java.util.List;

import javax.validation.Valid;

import org.lu.entity.CustomerProfileOperateVo;
import org.lu.entity.CustomerProfileVo;
import org.lu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerProfiles")
public class CustomerProfileController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CustomerProfileVo> getAllCustomer() {
		return customerService.findCustomerProfile();
	}

	@RequestMapping(value="/", method = RequestMethod.POST)
	public CustomerProfileVo saveCustomer(
			@RequestBody @Valid CustomerProfileOperateVo customerProfileCreateVo) {
		return customerService.saveCustomerProfile(customerProfileCreateVo);
	}

	@RequestMapping(value = "/{customerProfileId}", method = RequestMethod.PUT)
	public CustomerProfileVo updateCustomer(@PathVariable long customerProfileId,
			@RequestBody @Valid CustomerProfileOperateVo customerProfileCreateVo) {
		return customerService.updateCustomerProfile(customerProfileId,
				customerProfileCreateVo);
	}

	@RequestMapping(value = "/{customerProfileId}", method = RequestMethod.DELETE)
	public String updateCustomer(@PathVariable long customerProfileId) {

		// TODO use logic delete
		customerService.deleteeCustomerProfile(customerProfileId);
		return "deleted";
	}
}
