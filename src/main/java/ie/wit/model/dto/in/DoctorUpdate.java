package ie.wit.model.dto.in;

import ie.wit.model.entity.DoctorEntity;

/**
 * This class represents a data transfer object for a doctor that will be updated.
 *
 * @author Joe Wemyss
 */
public class DoctorUpdate extends DoctorInDto
{
	/**
	 * The primary key of the doctor
	 */
	private Long id;

	/**
	 * {@inheritDoc}
	 */
	public DoctorUpdate()
	{
	}

	/**
	 * {@inheritDoc}
	 */
	public DoctorUpdate(Long id, String firstName, String surname, String streetAddress, String townAddress, String contactNumber)
	{
		super(firstName, surname, streetAddress, townAddress, contactNumber);
		this.id = id;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}


	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DoctorEntity getAsEntity()
	{
		DoctorEntity doc = super.getAsEntity();
		doc.setId(id);
		return doc;
	}
}
