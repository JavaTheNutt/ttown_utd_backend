package ie.wit.model.dto.out;

/**
 * This class is the details of a user that will be sent back after a successful login.
 * 
 * @author Joe Wemyss
 */
public class UserOutDto 
{
  //TODO: uncomment when db and userentity are refactored.
  /**
   * The users email address
   */
  private String emailAddress;
  
  /**
   * The users first name if present, otherwise unknown
   */
  private String firstName;
  
  /**
   * The users surname if present, otherwise unknown
   */
  private String surname;
  
  /**
   * The Role of the user
   */
  //private String Role;
  
  /**
   * Constructor.
   * This object will only be able to be created by passing user details from the database to the constructor.
   * 
   * @param user  a UserEntity retrieved from the database
   */
  public UserOutDto(UserEntity user){
  	this.emailAddress = user.getEmailAddress();
  	this.firstName = user.getFirstName().orElse("Unknown"); //if does not exist, set value to unknown
  	this.surname = user.getSurname().orElse("Unknown");//if does not exist, set value to unknown
  	//this.role = user.getRole();
  }
  
  /**
   * Accessor for email address.
   * 
   * @return the email address
   */
  public String getEmailAddress(){
  	return this.emailAddress;
  }
  
  /**
   * Accessor for first name.
   * 
   * @return the users first name
   */
  public String getFirstName(){
  	return this.firstName;
  }
  
  /**
   * Accessor for surname.
   * 
   * @return the users surname
   */
  public String getSurname(){
  	return this.surname;
  }
  
  /**
   * Accessor for role.
   * 
   * @return the users role.
   */
   /*public String getRole(){
     return this.role
   }*/
}
