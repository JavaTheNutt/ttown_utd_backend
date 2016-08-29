package ie.wit.service.access;

import ie.wit.model.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This will test the {@link UserService}.
 *
 * @author Joe Wemyss
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService
{
	@Autowired
	private UserService userService;

	@Test
	public void testGetAll()
	{
		List<UserEntity> users = userService.getUsers();
		assertTrue("No Users", users.size() > 0);
	}

	@Test
	public void testGetOne()
	{
		UserEntity user = userService.getOneUserByEmail("joewemyss3@gmail.com");
		assertNotNull("Object is null", user);
	}
}
