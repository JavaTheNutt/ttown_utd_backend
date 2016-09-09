package ie.wit.model.entity;
/**
 * This class represents a family that JPA will persist to the database.
 * 
 * @author Joe Wemyss
 */
@Entity
@Table(name = "family")
public class FamilyEntity{
	/**
	 * Entity primary key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * The name of the family
	 */
	@Column(name = "family_name")
	@Basic(optional = false)
	private String familyName;
	
	/**
	 * The street address of the family
	 */
	@Column(name = "street_address")
	private String streetAddress;
	
	/**
	 * The town address of the family
	 */
	@Column(name = "town_address")
	private String townAddress;
	
	/**
	 * How much of the fee has been paid so far
	 */
	@Column(name = "paid_so_far")
	private Float paidSoFar;
	
	/**
	 * If the family is willing to volunteer
	 */
	@Column(name = "willing_to_volunteer")
	private Boolean willingToVolunteer;
	
	/**
	 * The foreign key for the one to many relationship with the doctor
	 */
	@Column(name = "doctor")
	private Long doctor;
	
	//todo getters and setters 
	
	public FamilyInDto(){
		
	}
	
	public FamilyInDto(String familyName, String streetAddress, String townAddress, Float paidSoFar, Boolean willingToVolunteer, Long doctor){
		this.familyName = familyName;
		this.streetAddress - streetAddress;
		this.townAddress = townAddress;
		this.paidSoFar = paidSoFar;
		this.willingToVolunteer = willingToVolunteer;
		this.doctor = doctor;
	}
}
