package ie.wit.service.access;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This class tests JWT generation.
 *
 * @author Joe Wemyss
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJwtService
{
	@Autowired
	private JwtService jwtService;

	@Test
	public void testRequestJwt(){
		String jwt = jwtService.requestJwt("joewemyss3@gmail.com", "Admin");
		assertNotNull("Jwt is null", jwt);
	}
	@Test
	public void testValidateJwt(){
		String jwt = jwtService.requestJwt("joewemyss3@gmail.com", "ADMIN");
		assertTrue("JWT not valid", jwtService.validateAdmin(jwt));
	}
}
