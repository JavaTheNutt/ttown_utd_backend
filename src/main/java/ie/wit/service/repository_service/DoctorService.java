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
	 * Persist a doctor to the database
	 * 
	 * @param docIn the doctor in inward dto form
	 * @return the doctor in outward dto form
	 */
	public DoctorOutDto insertOneDoctor(DoctorInDto docIn)
	{
		return new DoctorOutDto(doctorRepo.saveAndFlush(docIn.getAsEntity()));
	}
}
