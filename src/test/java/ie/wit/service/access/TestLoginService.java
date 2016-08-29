package ie.wit.service.access;

import ie.wit.model.dto.in.LoginDto;
import ie.wit.model.entity.UserEntity;
import ie.wit.repository.RoleRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This will test the login service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoginService
{
	/**
	 * The email address to be used for testing
	 */
	private static final String EMAIL_ADDRESS = "joe.wemyss@gmail.com";
	/**
	 * The password to be used for testing
	 */
	private static final String PLAIN_PASSWORD = "IAmJoe";
	/**
	 * The instance of {@link LoginService}
	 */
	@Autowired
	private LoginService loginService;
	/**
	 * The instance of the {@link RoleRepo}
	 */
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private UserService userService;

	/**
	 * This method will test the process of logging in
	 */
	@Test
	public void testLogin()
	{
		try {
			//create a user
			UserEntity user = new UserEntity(EMAIL_ADDRESS, PLAIN_PASSWORD);
			//create a set of login details based on the user
			LoginDto loginDto = new LoginDto(user.getEmailAddress(), user.getPassword());
			//add the admin role
			user.addRole(roleRepo.findByName("Admin"));
			//add the user to the database
			userService.addUser(user);
			//retrieve a JWT
			String jwt = loginService.login(loginDto);
			//assert that the JWT is not null
			assertNotNull("The returned jwt is null", jwt);
			//check that the JWT is valid
			assertTrue("The JWT does not match", loginService.validateJwt(jwt));
		} finally {
			//delete the created user
			userService.deleteUser(EMAIL_ADDRESS);
		}
	}

}
