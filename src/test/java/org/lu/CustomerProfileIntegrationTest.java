package org.lu;

import org.junit.runner.RunWith;
import org.lu.entity.CustomerProfileOperateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("integrationtest")
@SpringBootTest(classes = { CustomerApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerProfileIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private final static String BASE_URL = "http://localhost:";

	public void createCustomerTest() {
		CustomerProfileOperateVo requestVo = CustomerProfileOperateVo.builder()
				.build();
		
	}
	
//	public CustomerProfileOperateVo preparePrarameter(String firstName, String lastName, int dayOfBirth, int monthOfBirth, int yearOfBirth){
//		
//	}
}
