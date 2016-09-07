package ie.wit.model.dto.out;

import ie.wit.model.entity.DoctorEntity;

import javax.validation.constraints.NotNull;

/**
 * This will be the representation of a doctor that will be sent out via the doctor endpoint
 *
 * @author Joe Wemyss
 */
public class DoctorOutDto
{
	/**
	 * Representation of the primary key.
	 * Cannot be null;
	 */
	@NotNull
	private Long id;

	/**
	 * The docotrs first name.
	 */
	private String firstName;

	/**
	 * The docotrs surname.
	 */
	private String surname;

	/**
	 * The docotrs street address.
	 */
	private String streetAddress;

	/**
	 * The docotrs town address.
	 * Cannot be null.
	 * Defaults to "Thomastown" in the database
	 */
	@NotNull
	private String townAddress;
	/**
	 * The doctors contactNumber.
	 */
	private String contactNumber;

	/**
	 * Default constructor
	 */
	public DoctorOutDto()
	{
	}

	/**
	 * Constructor using the persisted entity.
	 *
	 * @param doctor the persisted entity for doctor
	 */
	public DoctorOutDto(DoctorEntity doctor)
	{
		this.id = doctor.getId();
		this.firstName = doctor.getFirstName().orElse("Unknown");
		this.surname = doctor.getSurname().orElse("Unknown");
		this.streetAddress = doctor.getStreetAddress().orElse("Unknown");
		this.townAddress = doctor.getTownAddress();
		this.contactNumber = doctor.getContactNumber();
	}

	/**
	 * Instantiates a new Doctor out dto.
	 *
	 * @param id            the id
	 * @param firstName     the first name
	 * @param surname       the surname
	 * @param streetAddress the street address
	 * @param townAddress   the town address
	 * @param contactNumber the contact number
	 */
	public DoctorOutDto(Long id, String firstName, String surname, String streetAddress, String townAddress, String contactNumber)
	{
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.streetAddress = streetAddress;
		this.townAddress = townAddress;
		this.contactNumber = contactNumber;
	}

	/**
	 * Accessor for primary key.
	 *
	 * @return the primary key
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * Mutator for primary key.
	 *
	 * @param id the primary key
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * Accessor for firstName.
	 *
	 * @return doctor first name, wrapped in a java 8 Optional to prevent NPE
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Mutator for firstName.
	 *
	 * @param firstName the doctors first name
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Accessor for surname.
	 *
	 * @return doctor surname, wrapped in a java 8 Optional to prevent NPE
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * Mutator for surname.
	 *
	 * @param surname the doctors surname
	 */
	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	/**
	 * Accessor for streetAddress.
	 *
	 * @return doctor street address, wrapped in a java 8 Optional to prevent NPE
	 */
	public String getStreetAddress()
	{
		return streetAddress;
	}

	/**
	 * Mutator for streetAddress.
	 *
	 * @param streetAddress the doctors street address
	 */
	public void setStreetAddress(String streetAddress)
	{
		this.streetAddress = streetAddress;
	}

	/**
	 * Accessor for townAddress.
	 *
	 * @return doctor town address
	 */
	public String getTownAddress()
	{
		return townAddress;
	}

	/**
	 * Mutator for townAddress.
	 * Sets to defualt value if null.
	 *
	 * @param townAddress the doctors town address
	 */
	public void setTownAddress(String townAddress)
	{
		this.townAddress = townAddress == null ? "Thomastown" : townAddress;
	}

	/**
	 * Accessor for contactNumber
	 *
	 * @return doctor contact number
	 */
	public String getContactNumber()
	{
		return contactNumber;
	}

	/**
	 * Mutator for contactNumber.
	 *
	 * @param contactNumber the doctors contact number
	 */
	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString()
	{
		return "DoctorOutDto{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", surname='" + surname + '\'' +
				", streetAddress='" + streetAddress + '\'' +
				", townAddress='" + townAddress + '\'' +
				", contactNumber='" + contactNumber + '\'' +
				'}';
	}
}
