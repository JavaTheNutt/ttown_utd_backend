package ie.wit.model.entity;

import javax.persistence.*;

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

	/**
	 * Instantiates a new Family entity.
	 */
	public FamilyEntity(){
		
	}

	/**
	 * Instantiates a new Family entity.
	 *
	 * @param familyName         the family name
	 * @param streetAddress      the street address
	 * @param townAddress        the town address
	 * @param paidSoFar          the paid so far
	 * @param willingToVolunteer the willing to volunteer
	 * @param doctor             the doctor
	 */
	public FamilyEntity(String familyName, String streetAddress, String townAddress, Float paidSoFar, Boolean willingToVolunteer, Long doctor){
		this.familyName = familyName;
		this.streetAddress = streetAddress;
		this.townAddress = townAddress;
		this.paidSoFar = paidSoFar;
		this.willingToVolunteer = willingToVolunteer;
		this.doctor = doctor;
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
	 * Gets family name.
	 *
	 * @return the family name
	 */
	public String getFamilyName()
	{
		return familyName;
	}

	/**
	 * Sets family name.
	 *
	 * @param familyName the family name
	 */
	public void setFamilyName(String familyName)
	{
		this.familyName = familyName;
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
	 * Gets paid so far.
	 *
	 * @return the paid so far
	 */
	public Float getPaidSoFar()
	{
		return paidSoFar;
	}

	/**
	 * Sets paid so far.
	 *
	 * @param paidSoFar the paid so far
	 */
	public void setPaidSoFar(Float paidSoFar)
	{
		this.paidSoFar = paidSoFar;
	}

	/**
	 * Gets willing to volunteer.
	 *
	 * @return the willing to volunteer
	 */
	public Boolean getWillingToVolunteer()
	{
		return willingToVolunteer;
	}

	/**
	 * Sets willing to volunteer.
	 *
	 * @param willingToVolunteer the willing to volunteer
	 */
	public void setWillingToVolunteer(Boolean willingToVolunteer)
	{
		this.willingToVolunteer = willingToVolunteer;
	}

	/**
	 * Gets doctor.
	 *
	 * @return the doctor
	 */
	public Long getDoctor()
	{
		return doctor;
	}

	/**
	 * Sets doctor.
	 *
	 * @param doctor the doctor
	 */
	public void setDoctor(Long doctor)
	{
		this.doctor = doctor;
	}
}
