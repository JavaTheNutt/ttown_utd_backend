package ie.wit.model.entity;

/**
 * This class represents a doctor entity that JPA will persist to the database
 *
 * @author Joe Wemyss
 */
 @Entity
 @Table(name = "doctor")
public class DoctorEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "street_address")
	private String streetAddress;
	
	@Column(name = "town_address")
	private String townAddress;
	
	@Column(name = "contact_number")
	private String contactNumber;
}
