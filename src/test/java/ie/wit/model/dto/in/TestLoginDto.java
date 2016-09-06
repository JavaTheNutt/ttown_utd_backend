package ie.wit.model.dto.in;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the login data transfer object.
 *
 * @author Joe Wemyss
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLoginDto
{
	/**
	 * This method will test the creation of a login data transfer object.
	 */
	@Test
	public void testCreateLoginDto()
	{
		LoginDto login = new LoginDto("joewemyss3@gmail.com", "IAmJoe");
		assertEquals("Email does not match", "joewemyss3@gmail.com", login.getEmailAddress());
		assertEquals("Password does not match", "IAmJoe", login.getPassword());
	}
}
