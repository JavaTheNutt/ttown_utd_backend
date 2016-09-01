package ie.wit.model.dto.out;

import ie.wit.model.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the User data tranfer object out.
 * 
 * @author Joe Wemyss
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserOutDto
{
	/**
	 * Test creating a UserOutDto from a user entity.
	 */
	@Test
	public void testCreate(){
		UserEntity user = new UserEntity("joe.wemyss@gmail.com", "MeJoe", 1);
		UserOutDto userOut  = new UserOutDto(user);
		
		UserEntity user2 = new UserEntity("me@me.com", "MeJoe", 2);
		user2.setFirstName("Joe");
		user2.setSurname("Wemyss");
		UserOutDto userOut2 = new UserOutDto(user2);
		
		UserEntity user3 = new UserEntity("you@you.com", "MeJoe", 3);
		UserOutDto userOut3 = new UserOutDto(user3);
		
		
		assertEquals("User 1 email does not match", "joe.wemyss@gmail.com", userOut.getEmailAddress());
		assertEquals("User 2 email does not match", "me@me.com", userOut2.getEmailAddress());
		
		assertEquals("User 1 role does not match", "ADMIN", userOut.getRole());
		assertEquals("User 2 role does not match", "WRITE", userOut2.getRole());
		assertEquals("User 3 role does not match", "READ", userOut3.getRole());
		
		assertEquals("User 1 first name does not match", "Unknown", userOut.getFirstName());
		assertEquals("User 2 first name does not match", "Joe", userOut2.getFirstName());
		
		assertEquals("User 1 surname does not match", "Unknown", userOut.getSurname());
		assertEquals("User 2 surname does not match", "Wemyss", userOut2.getSurname());
	}
}
