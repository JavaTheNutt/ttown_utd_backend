package ie.wit.service.repository_service;

import ie.wit.model.dto.in.DoctorInDto;
import ie.wit.model.dto.in.DoctorUpdate;
import ie.wit.model.dto.out.DoctorOutDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

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
	public void testUpdate()
	{
		DoctorOutDto docOut = null;
		try {
			DoctorInDto docIn = new DoctorInDto("James", "Drynan", "Low Street", null, "05123456");
			docOut = doctorService.insertOneDoctor(docIn);
			assertEquals("The first name does not match", "James", docOut.getFirstName());
			DoctorUpdate docIn2 = new DoctorUpdate(docOut.getId(), "John", docOut.getSurname(), docOut.getStreetAddress(), docOut.getTownAddress(), docOut.getContactNumber());
			DoctorOutDto docOut2 = doctorService.updateDoctor(docIn2);
			assertEquals("The first name does not match", "John", docOut2.getFirstName());
			assertEquals("The town address does not match", "Thomastown", docOut2.getTownAddress());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doctorService.deleteDoctor(docOut.getId());
		}
	}
}
