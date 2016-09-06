package ie.wit.service.repository_service;

import ie.wit.model.dto.out.DoctorOutDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by joewe on 06/09/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDoctorService
{
	@Autowired
	private DoctorService doctorService;

	@Test
	public void testGetOne()
	{
		DoctorOutDto doctor = doctorService.getOneDoctor(1L);
		assertTrue("First name does not match", doctor.getFirstName().isPresent());
	}
}
