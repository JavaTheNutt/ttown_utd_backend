package ie.wit.model.dto.out;

import ie.wit.model.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by joewe on 31/08/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserOutDto
{
	@Test
	public void testCreate(){
		UserEntity user = new UserEntity("joe.wemyss@gmail.com", "MeJoe", 1);
		UserOutDto userOut  = new UserOutDto(user);
		assertEquals("User email does not match", "joe.wemyss@gmail.com", userOut.getEmailAddress());
		assertEquals("User role does not match", Integer.valueOf(1), userOut.getRole());
		assertEquals("User first name does not match", "Unknown", userOut.getFirstName());
	}
}
