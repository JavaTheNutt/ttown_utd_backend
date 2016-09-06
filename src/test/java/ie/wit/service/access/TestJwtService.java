package ie.wit.service.access;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * This class tests JWT generation.
 *
 * @author Joe Wemyss
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJwtService
{
	private static final String USERNAME = "joewemyss3@gmail.com";
	private static final String ADMIN_ROLE = "Admin";

	/**
	 * Autowired reference to the {@link JwtService}
	 */
	@Autowired
	private JwtService jwtService;

	/**
	 * Test requesting a JWT
	 */
	@Test
	public void testRequestJwt()
	{
		String jwt = jwtService.requestJwt("joewemyss3@gmail.com", "Admin");
		assertNotNull("Jwt is null", jwt);
	}

	/**
	 * Test creating and validating a JWT
	 */
	@Test
	public void testValidateJwt()
	{
		String jwt = jwtService.requestJwt("joewemyss3@gmail.com", "ADMIN");
		assertTrue("JWT not valid", jwtService.validateAdmin(jwt));
	}

	@Test
	public void testGetUsernameAndRole()
	{
		String jwt = jwtService.requestJwt(USERNAME, ADMIN_ROLE);
		Map<String, String> details = jwtService.getUsernameAndRole(jwt);
		assertEquals("The username is incorrect", USERNAME, details.get("user"));
		assertEquals("The Role is incorrect", ADMIN_ROLE, details.get("role"));
	}

	@Test
	public void testGetNewJwt()
	{
		String jwt = jwtService.requestJwt(USERNAME, ADMIN_ROLE);
		String newJwt = jwtService.requestNewJwt(jwt);
		Map<String, String> details = jwtService.getUsernameAndRole(newJwt);
		assertEquals("The username is incorrect", USERNAME, details.get("user"));
		assertEquals("The Role is incorrect", ADMIN_ROLE, details.get("role"));
	}
}
