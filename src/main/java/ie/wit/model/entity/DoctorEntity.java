package ie.wit.model.entity;

import javax.persistence.*;

/**
 * This class represents a doctor entity that JPA will persist to the database
 *
 * @author Joe Wemyss
 */
 @Entity
 @Table(name = "doctor")
public class DoctorEntity
{
	/**
	 * The Entity Primary key. Generated by the database
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * The doctors first name
	 */
	@Column(name = "first_name")
	private String firstName;
	
	/**
	 * The doctors surname
	 */
	@Column(name = "surname")
	private String surname;
	
	/**
	 * The doctors street address
	 */
	@Column(name = "street_address")
	private String streetAddress;
	
	/**
	 * The doctors town address
	 */
	@Column(name = "town_address")
	private String townAddress;
	
	/**
	 * The doctors contact number
	 */
	@Column(name = "contact_number")
	private String contactNumber;
	
	/**
	 * Default constructor.
	 * Used by JPA to initialize the Entity
	 */
	public DoctorEntity(){
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param firstName the doctors first name
	 * @param surname the doctors surname
	 */
	public DoctorEntity(String firstName, String surname, String streetAddress, String townAddress, String contactNumber){
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
	public Long getId(){
		return this.id;	
	}
	
	/**
	 * Accessor for first name
	 * 
	 * @return the first name
	 */
	public String getFirstName(){
		return this.firstName;
	}
	
	/**
	 * Accessor for surname.
	 * 
	 * @return the surname
	 */
	public String getSurname(){
		return this.surname;
	}
	
	/**
	 * Accessor for streetAddress.
	 * 
	 * @return the street address
	 */
	public String getStreetAddress(){
		return this.streetAddress;
	}
	
	/**
	 * Accessor for town address
	 * 
	 * @return the town address
	 */
	public String getTownAddress(){
		return this.townAddress;
	}
	
	/**
	 * Accessor for contact number
	 * 
	 * @return the contact number
	 */
	public String getContactNumber(){
		return this.contactNumber;
	}
	
	/**
	 * Mutator for the primary key
	 * 
	 * @param id the new id
	 */
	public void setId(Long id){
		this.id = id;
	}
	
	/**
	 * Mutator for the first name
	 * 
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	/**
	 * Mutator for the surname
	 * 
	 * @param surname the new surname
	 */
	public void setSurname(String surname){
		this.surname = surname;
	}
	
	/**
	 * Mutator for the street address
	 * 
	 * @param streetAddress the new street address
	 */
	public void setStreetAddress(String streetAddress){
		this.streetAddress = streetAddress;
	}
	
	/**
	 * Mutator for the town address 
	 * 
	 * @param townAddress the new town address
	 */
	public void setTownAddress(String townAddress){
		this.townAddress = townAddress;
	}
	
	/**
	 * Mutator for the contact nnumber 
	 * 
	 * @param contactNumber the new contact number
	 */
	public void setContactNumber(String contactNumber){
		this.contactNumber  = contactNumber;
	}
	
	//TODO: generate toString() for testing
}
