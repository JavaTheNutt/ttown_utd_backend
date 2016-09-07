package ie.wit.model.dto.in;

import org.hibernate.validator.constraints.NotEmpty;
import ie.wit.model.entity.DoctorEntity;

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
     * Constructor
     */
    public DoctorInDto(String firstName, String surname, String streetAddress, String townAddress, String contactNumber)
    {
        this.firstName = firstName;
        this.surname = surname;
        this.streetAddress = streetAddress;
        this.townAddress = townAddress == null ? townAddress : "Thomastown";
        this.contactNumber = contactNumber;
    }
    
    //todo: getters, setters and toString
    /**
     * Return the doctor in a form that JPA can persist to the database
     */
    public DoctorEntity getAsEntity()
    {
        return new DoctorEntity(firstName, surname, streetAddress, townAddress, contactNumber);
    }
}
