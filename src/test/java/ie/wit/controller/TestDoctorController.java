package ie.wit.controller;

import ie.wit.model.dto.in.LoginDto;
import ie.wit.model.dto.out.UserOutDto;
import ie.wit.service.access.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the login controller
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDoctorController
{
	@Autowired
	private LoginService loginService;
	@Autowired
	private DoctorController doctorController;

	@Test
	public void test(){
		assertTrue(true);
	}
}
