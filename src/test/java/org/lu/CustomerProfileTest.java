package org.lu;

import org.junit.runner.RunWith;
import org.lu.repository.CustomerProfileRepository;
import org.lu.service.CustomerService;
import org.lu.service.impl.CustomerServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("unittest")
public class CustomerProfileTest {

	@Mock
	CustomerProfileRepository customerProfileRepository;

	@InjectMocks
	CustomerService CustomerService = new CustomerServiceImpl();

	public void createCustemerProfileTest() {

	}

}
