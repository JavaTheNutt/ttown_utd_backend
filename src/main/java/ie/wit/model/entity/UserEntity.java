package ie.wit.model.entity;

import javax.persistence.*;
import java.util.Optional;

/**
 * Represents a user entity for authentication
 *
 * @author Joe Wemyss
 */
@Entity
@Table(name = "User")
public class UserEntity
{
	/**
	 * The Entity Primary key. Generated by the database
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Users email address.
	 * Must be unique, cannot be null
	 */
	@Basic(optional = false)
	@Column(name = "email_address", unique = true)
	private String emailAddress;

	/**
	 * Hash of the users password.
	 * Cannot be null
	 */
	@Basic(optional = false)
	@Column(name = "password")
	private String password;


	/**
	 * Users first name
	 * Nullable
	 */
	@Column(name = "first_name")
	private String firstName;

	/**
	 * Users surname
	 * Nullable
	 */
	@Column(name = "surname")
	private String surname;


	/**
	 * This will represent the foreign key relation between user and role.
	 */
	@Basic(optional = false)
	@Column(name = "role")
	private Integer role;

	/**
	 * Default constructor.
	 * Used by JPA to initialize the Entity
	 */
	protected UserEntity()
	{
	}

	/**
	 * Constructor.
	 *
	 * @param emailAddress the username
	 * @param password     the hashed password
	 */
	public UserEntity(String emailAddress, String password, Integer role)
	{
		this.emailAddress = emailAddress;
		this.password = password;
		this.role = role;
	}

	/**
	 * Accessor for first name.
	 *
	 * @return optional string containing the first name which may be null
	 */
	public Optional<String> getFirstName()
	{
		return Optional.ofNullable(firstName);
	}

	/**
	 * set the users first name.
	 *
	 * @param firstName the users first name
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Accessor for surname.
	 *
	 * @return optional string containing the surname which may be null
	 */
	public Optional<String> getSurname()
	{
		return Optional.ofNullable(surname);
	}

	/**
	 * set the users surname.
	 *
	 * @param surname the users surname
	 */
	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	/**
	 * Accessor for Id.
	 *
	 * @return the primary key
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * Set the id.
	 * Not currently required by JPA, but provided to increase robustness.
	 *
	 * @param id the primary key
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * Accessor for emailAddress.
	 *
	 * @return the emailAddress
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}

	/**
	 * Set the email address of the user.
	 *
	 * @param emailAddress the email address
	 */
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	/**
	 * Accessor for the password.
	 *
	 * @return the hash of the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * set the users password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * Accessor for role.
	 *
	 * @return the role of the user
	 */
	public Integer getRole()
	{
		return this.role;
	}

	/**
	 * Mutator for role.
	 *
	 * @param role the role of the user
	 */
	public void setRole(Integer role)
	{
		this.role = role;
	}
}
