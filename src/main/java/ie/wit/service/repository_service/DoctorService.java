package ie.wit.service.repository_service;

import ie.wit.model.dto.in.DoctorInDto;
import ie.wit.model.dto.in.DoctorUpdate;
import ie.wit.model.dto.out.DoctorOutDto;
import ie.wit.repository.DoctorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This will be the service that will act as a link between the {@link DoctorService} and the {@link ie.wit.controller.DoctorController}
 */
@Service
public class DoctorService
{
	/**
	 * Logger for this class.
	 */
	private Logger logger = LoggerFactory.getLogger(DoctorService.class);

	/**
	 * autowired reference to the {@link DoctorRepo}
	 */
	private DoctorRepo doctorRepo;

	/**
	 * Autowired constructor.
	 *
	 * @param doctorRepo the DoctorRepo
	 */
	public DoctorService(DoctorRepo doctorRepo)
	{
		this.doctorRepo = doctorRepo;
	}

	/**
	 * Return a single doctor
	 *
	 * @param id the primarykey of the requested doctor
	 * @return the doctor in a transferable format
	 */
	public DoctorOutDto getOneDoctor(Long id)
	{
		logger.info("Getting doctor with an id of " + id);
		return new DoctorOutDto(doctorRepo.findOne(id));
	}

	/**
	 * Get all doctors.
	 *
	 * @return a list of doctors
	 */
	public List<DoctorOutDto> getAllDoctors()
	{
		logger.info("Getting all doctors");
		return doctorRepo.findAll().stream().map(DoctorOutDto::new).collect(Collectors.toList());
	}

	/**
	 * Persist a doctor to the database
	 *
	 * @param docIn the doctor in inward dto form
	 * @return the doctor in outward dto form
	 */
	public DoctorOutDto insertOneDoctor(DoctorInDto docIn)
	{
		logger.info("Inserting new doctor");
		return new DoctorOutDto(doctorRepo.saveAndFlush(docIn.getAsEntity()));
	}

	/**
	 * Delete a doctor from the database.
	 *
	 * @param docIn the doctor to be deleted
	 */
	public void deleteDoctor(DoctorUpdate docIn)
	{
		logger.info("Deleting a doctor");
		doctorRepo.delete(docIn.getId());
	}

	/**
	 * Delete a doctor from the database.
	 *
	 * @param id the id of the doctor ot be deleted.
	 */
	public void deleteDoctor(Long id)
	{
		logger.info("Deleting a doctor with an id of " + id);
		doctorRepo.delete(id);
	}

	/**
	 * Update a doctor.
	 *
	 * @param doctorIn the doctor to be updated
	 * @return the updated doctor in outward data tranfer object format
	 */
	public DoctorOutDto updateDoctor(DoctorUpdate doctorIn)
	{
		logger.info("Updating a doctor with an id of " + doctorIn.getId());
		return new DoctorOutDto(doctorRepo.saveAndFlush(doctorIn.getAsEntity()));
	}
}
