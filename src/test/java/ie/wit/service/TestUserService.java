package ie.wit.service;

import ie.wit.model.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by joewe on 25/08/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService
{
	@Autowired
	private UserService userService;

	@Test
	public void TestGetAllUsers()
	{
		List<UserEntity> users = userService.getUsers();
		assertTrue("User does not have role", users.get(0).hasRole("Admin"));
	}
}
