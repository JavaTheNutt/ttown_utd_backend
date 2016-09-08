package ie.wit.service.access;

import ie.wit.model.dto.in.LoginDto;
import ie.wit.model.dto.temp_transfer.UserJwtTransfer;
import ie.wit.model.entity.UserEntity;
import ie.wit.model.enums.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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
	private static final String ADMIN_EMAIL_ADDRESS = "joe.wemyss@gmail.com";
	private static final String WRITE_EMAIL_ADDRESS = "michelle.power@gmail.com";
	private static final String READ_EMAIL_ADDRESS = "ag.wemyss@gmail.com";
	/**
	 * The password to be used for testing
	 */
	private static final String PLAIN_PASSWORD = "IAmJoe";
	/**
	 * The instance of {@link LoginService}
	 */
	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	/**
	 * This method will test the process of logging in
	 */
	@Test
	public void testAdminLogin()
	{
		try {
			//create a user
			UserEntity admin = new UserEntity(ADMIN_EMAIL_ADDRESS, PLAIN_PASSWORD, 1);
			//create a set of login details based on the user
			LoginDto loginDto = new LoginDto(admin.getEmailAddress(), admin.getPassword());
			//add the user to the database
			userService.addUser(admin);
			//retrieve a JWT
			UserJwtTransfer adminUserJwtTransfer = loginService.login(loginDto);
			//assert that the JWT is not null
			assertNotNull("The returned jwt is null", adminUserJwtTransfer);
			//check that the JWT is valid
			assertTrue("The JWT does not match", loginService.validateJwt(adminUserJwtTransfer.getJwt()));
			assertEquals("Incorrect role returned", "ADMIN", adminUserJwtTransfer.getUser().getRole());
			assertEquals("The email address is incorrect", ADMIN_EMAIL_ADDRESS, adminUserJwtTransfer.getUser().getEmailAddress());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//delete the created user
			userService.deleteUser(ADMIN_EMAIL_ADDRESS);
		}
	}
	
	@Test
	public void testWriteLogin()
	{
		try{
			UserEntity write = new UserEntity(WRITE_EMAIL_ADDRESS, PLAIN_PASSWORD, 2);
			LoginDto loginDto = new LoginDto(write.getEmailAddress(), write.getPassword());
			userService.addUser(write);
			UserJwtTransfer writeUserJwtTransfer = loginService.login(loginDto);
			
			assertEquals("Incorrect Role returned", "WRITE", writeUserJwtTransfer.getUser().getRole());
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			userService.deleteUser(WRITE_EMAIL_ADDRESS);
		}
	}
	@Test
	public void testReadLogin()
	{
		try{
			UserEntity read = new UserEntity(READ_EMAIL_ADDRESS, PLAIN_PASSWORD, 2);
			LoginDto loginDto = new LoginDto(read.getEmailAddress(), read.getPassword());
			userService.addUser(read);
			UserJwtTransfer readUserJwtTransfer = loginService.login(loginDto);
			
			assertEquals("Incorrect Role returned", "WRITE", readUserJwtTransfer.getUser().getRole());
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			userService.deleteUser(READ_EMAIL_ADDRESS);
		}
	}
}
