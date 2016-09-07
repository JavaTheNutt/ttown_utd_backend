package ie.wit.service.repository_service;

import ie.wit.model.dto.out.DoctorOutDto;
import ie.wit.repository.DoctorRepo;
import org.springframework.stereotype.Service;

/**
 * This will be the service that will act as a link between the {@link DoctorService} and the {@link ie.wit.controller.DoctorController}
 */
@Service
public class DoctorService
{
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
		return new DoctorOutDto(doctorRepo.findOne(id));
	}
	
	/**
	 * Get all doctors.
	 * 
	 * @return a list of doctors
	 */
	public List<DoctorOutDto> getAllDoctors()
	{
		return doctorRepo.findAll().stream().map(doc -> new DoctorOutDto(doc)).collect(Arrays.asList());
	}
	
	/**
	 * Persist a doctor to the database
	 * 
	 * @param docIn the doctor in inward dto form
	 * @return the doctor in outward dto form
	 */
	public DoctorOutDto insertOneDoctor(DoctorInDto docIn)
	{
		return new DoctorOutDto(doctorRepo.saveAndFlush(docIn.getAsEntity()));
	}
	
	/**
	 * Delete a doctor from the database.
	 * 
	 * @param docIn the doctor to be deleted
	 */
	public void deleteDoctor(DoctorUpdate docIn)
	{
		doctorRepo.delete(docIn.getAsEntity);
	}
	
	/**
	 * Delete a doctor from the database.
	 * 
	 * @param id the id of the doctor ot be deleted.
	 */
	public void deleteDoctor(Long id)
	{
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
		return new DoctorOutDto(doctorRepo.saveAndFlush(doctorIn.getAsEntity()));
	}
}
