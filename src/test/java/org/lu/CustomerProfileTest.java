package org.lu;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lu.entity.CustomerAddressEntity;
import org.lu.entity.CustomerProfileEntity;
import org.lu.entity.CustomerProfileOperateVo;
import org.lu.entity.CustomerProfileVo;
import org.lu.repository.CustomerProfileRepository;
import org.lu.service.CustomerService;
import org.lu.service.impl.CustomerServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("unittest")
public class CustomerProfileTest {

	@Mock
	CustomerProfileRepository customerProfileRepository;

	@InjectMocks
	CustomerService CustomerService = new CustomerServiceImpl();

	CustomerProfileEntity profile;

	@Before
	public void setup() {

		profile = CustomerProfileEntity
				.builder()
				.firstName("Tom")
				.lastName("Fynn")
				.birthDay(
						Date.from(LocalDate.parse("1987-01-01").atStartOfDay()
								.toInstant(ZoneOffset.UTC)))
				.customerProfileId(1l)
				.address(
						CustomerAddressEntity.builder().homeAddress("home")
								.officeAddress("office")
								.emailAddress("tom@gmail.com").build()).build();
	}

	@Test
	public void createCustemerProfileTest() {
		Mockito.when(
				customerProfileRepository.save(Mockito
						.any(CustomerProfileEntity.class))).thenReturn(profile);

		CustomerProfileOperateVo requestVo = this.preparePrarameter("Tom",
				"Fynn", "1987-01-01", "home", "office", "Tom@gmail.com");

		CustomerProfileVo subscription = CustomerService
				.saveCustomerProfile(requestVo);

		assertThat(subscription.getFirstName()).isEqualTo("Tom");
		assertThat(subscription.getHomeAddress()).isEqualTo("home");
		assertThat(subscription.getBirthDay()).isEqualTo("1987-01-01");
	}

	private CustomerProfileOperateVo preparePrarameter(String firstName,
			String lastName, String dateOfBirth, String homeAddress,
			String officeAddress, String emailAddress) {

		return CustomerProfileOperateVo.builder().firstName(firstName)
				.lastName(lastName).birthDay(dateOfBirth)
				.homeAddress(homeAddress).officeAddress(officeAddress)
				.emailAddress(emailAddress).build();
	}

}
