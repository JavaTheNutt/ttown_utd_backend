package ie.wit.model.dto.out;

import javax.validation.constraints.NotNull;
import java.util.Optional;

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
	 * Full constructor
	 *
	 * @param id            the primary key of the entity
	 * @param firstName     the doctors first name
	 * @param surname       the doctors surname
	 * @param streetAddress the doctors street address
	 * @param townAddress   the doctors town address
	 * @param contactNumber the doctors contact number
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
	public Optional<String> getFirstName()
	{
		return Optional.ofNullable(firstName);
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
	public Optional<String> getSurname()
	{
		return Optional.ofNullable(surname);
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
	public Optional<String> getStreetAddress()
	{
		return Optional.ofNullable(streetAddress);
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
	 * @return doctor contact number, wrapped in a java 8 Optional to prevent NPE
	 */
	public Optional<String> getContactNumber()
	{
		return Optional.ofNullable(contactNumber);
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

	// TODO: Generate toString()
}
