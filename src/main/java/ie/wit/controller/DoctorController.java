package ie.wit.controller;

import ie.wit.model.dto.in.DoctorInDto;
import ie.wit.model.dto.out.DoctorOutDto;
import ie.wit.service.repository_service.DoctorService;
import ie.wit.service.util.response.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This will be the endpoint relating to Doctors
 *
 * @author Joe Wemyss
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController
{
	/**
	 * The logger for this class
	 */
	private Logger logger = LoggerFactory.getLogger(DoctorController.class);

	/**
	 * Autowired reference to the {@link DoctorService}
	 */
	private DoctorService doctorService;

	/**
	 * Autowired reference to the {@link ResponseService}
	 */
	private ResponseService responseService;

	/**
	 * Autowired constructor.
	 *
	 * @param doctorService   the doctor service
	 * @param responseService the response service
	 */
	@Autowired
	public DoctorController(DoctorService doctorService, ResponseService responseService)
	{
		this.doctorService = doctorService;
		this.responseService = responseService;
	}

	/**
	 * This is the endpoint for getting a single doctor by id.
	 *
	 * @param id     the primary key of the doctor to be retrieved
	 * @param oldJwt the jwt issued to the user with their last request
	 * @return the doctor with the specified id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<DoctorOutDto> getOne(@PathVariable Long id, @RequestHeader("auth") String oldJwt)
	{
		HttpHeaders headers = responseService.adjustHeaders(oldJwt, 100);
		DoctorOutDto doctor = doctorService.getOneDoctor(id);
		return new ResponseEntity<>(doctor, headers, HttpStatus.OK);
	}
	
	/**
	 * Endpoint for adding a doctor.
	 * 
	 * @param docIn the doctor to be added 
	 * @param oldJwt the jwt issued to the suer with the last request
	 * @return the doctor in outward dto format
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DoctorOutDto> addDoctor(@RequestBody @Valid DoctorInDto docIn, @RequestHeader("auth") String oldJwt){
		HttpHeaders headers = responseService.adjustHeaders(oldJwt, 100);
		DoctorOutDto doctor = doctorService.insertOneDoctor(docIn);
		return new ResponseEntity<>(doctor, headers, HttpStatus.OK);
	}
}
