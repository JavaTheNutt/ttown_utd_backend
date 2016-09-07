package ie.wit.model.dto.in;

import ie.wit.model.entity.DoctorEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * This class represents the inward data transfer object for the {@link DoctorEntity}
 *
 * @author Joe Wemyss
 */
public class DoctorInDto
{
	//todo: extend this class for updating objects? sub class could also have primary key property?
	/**
	 * The first name of the doctor
	 */
	private String firstName;

	/**
	 * The surname of the doctor
	 */
	private String surname;

	/**
	 * The street address of the doctor
	 */
	private String streetAddress;

	/**
	 * The town address of the doctor
	 */
	private String townAddress;

	/**
	 * The contact number of the doctor.
	 * Cannot be null or empty.
	 */
	@NotNull
	@NotEmpty
	private String contactNumber;

	/**
	 * The default constructor
	 */
	public DoctorInDto()
	{

	}

	/**
	 * Constructor.
	 *
	 * @param firstName     the doctors first name
	 * @param surname       the doctors surname
	 * @param streetAddress the doctors street address
	 * @param townAddress   the doctors town address
	 * @param contactNumber the doctors contact number
	 */
	public DoctorInDto(String firstName, String surname, String streetAddress, String townAddress, String contactNumber)
	{
		this.firstName = firstName;
		this.surname = surname;
		this.streetAddress = streetAddress;
		if (townAddress == null) {
			townAddress = "Thomastown";
		}
		this.townAddress = townAddress;
		this.contactNumber = contactNumber;
	}

	/**
	 * Gets first name.
	 *
	 * @return the first name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Sets first name.
	 *
	 * @param firstName the first name
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Gets surname.
	 *
	 * @return the surname
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * Sets surname.
	 *
	 * @param surname the surname
	 */
	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	/**
	 * Gets street address.
	 *
	 * @return the street address
	 */
	public String getStreetAddress()
	{
		return streetAddress;
	}

	/**
	 * Sets street address.
	 *
	 * @param streetAddress the street address
	 */
	public void setStreetAddress(String streetAddress)
	{
		this.streetAddress = streetAddress;
	}

	/**
	 * Gets town address.
	 *
	 * @return the town address
	 */
	public String getTownAddress()
	{
		return townAddress;
	}

	/**
	 * Sets town address.
	 *
	 * @param townAddress the town address
	 */
	public void setTownAddress(String townAddress)
	{
		this.townAddress = townAddress;
	}

	/**
	 * Gets contact number.
	 *
	 * @return the contact number
	 */
	public String getContactNumber()
	{
		return contactNumber;
	}

	/**
	 * Sets contact number.
	 *
	 * @param contactNumber the contact number
	 */
	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

	/**
	 * Return the doctor in a form that JPA can persist to the database.
	 *
	 * @return the doctor in a form that can be persisted to the database
	 */
	public DoctorEntity getAsEntity()
	{
		return new DoctorEntity(firstName, surname, streetAddress, townAddress, contactNumber);
	}
}
