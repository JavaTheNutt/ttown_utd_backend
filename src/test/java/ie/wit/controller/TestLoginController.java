package ie.wit.controller;

import ie.wit.model.dto.in.LoginDto;
import ie.wit.model.dto.out.UserOutDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class tests the login controller
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoginController{

	private Logger logger  = LoggerFactory.getLogger(TestLoginController.class);
	@Autowired
	private LoginController loginController;

	@Test
	public void testLogin(){
		ResponseEntity<UserOutDto> res = loginController.login(new HttpHeaders(), new LoginDto("joewemyss3@gmail.com", "IAmJoe"));
		UserOutDto user = res.getBody();
		String jwt = res.getHeaders().get("auth").get(0);
		logger.debug(jwt);
		assertNotNull("JWT is null", jwt);
		assertEquals("Email address does not match", user.getEmailAddress(), "joewemyss3@gmail.com");

	}
}
