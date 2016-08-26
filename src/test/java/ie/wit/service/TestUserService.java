package ie.wit.service;

import ie.wit.model.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This test class will test the {@link ie.wit.service.UserService } 
 * 
 * @author Joe Wemyss
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService
{
	/**
	 * Reference to the {@link ie.wit.service.UserService } 
	 */
	@Autowired
	private UserService userService;

	/**
	 * This will test {@link ie.wit.service.UserService#getUsers } 
	 */
	@Test
	public void TestGetAllUsers()
	{
		List<UserEntity> users = userService.getUsers();
		assertFalse("Collection is empty", users.isEmpty());
		assertTrue("User does not have role", users.get(0).hasRole("Admin"));
	}
}
