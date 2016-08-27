package ie.wit.service.access;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This class will test {@link HashingService}.
 *
 * @author Joe Wemyss
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHashingService
{
	private String test = "TestPassword";
	@Autowired
	private HashingService hashingService;

	@Test
	public void testGeneratePassword(){
		String password = hashingService.generatePasswordHash(test);
		assertNotNull("Returned password is null", password);
		assertEquals("Result is not right length", password.length(), 60);
	}
	@Test
	public void testMatchingPassword(){

		String hashedPassword = hashingService.generatePasswordHash(test);
		assertTrue("Passwords do not match", hashingService.checkPassword(test, hashedPassword));
	}
}
