package ie.wit.model.dto.in;
/**
 * This class represents the data that will be passed into the login controller when the user attempts to log in. Should match 
 * to a UserEntity
 * 
 * @see ie.wit.model.entity.UserEntity UserEntity
 * @author Joe Wemyss
 */
public class LoginDto
{
	/**
	 * The email address that is passed in
	 * Cannot be null or empty
	 */
	@NotNull
	@NotEmpty
	private String emailAddress;
	
	/**
	 * The password that is passed in
	 * Cannot be null or empty
	 */
	@NotNull
	@NotEmpty
	private String password;
	
	/**
	 * The default constructor used by jackson to create the entity from JSON
	 */
	public LoginDto(){
		
	}
	
	/**
	 * Constructor
	 * @param emailAddress the email address of the user attempting to log in
	 * @param password the users password of the user attempting to log in
	 */
	public LoginDto(String emailAddress, String password){
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	/**
	 * Accessor for emailAddress
	 * @return String emailAddress
	 */
	public String getEmailAddress(){
		return emailAddress;
	}
	
	/**
	 * Accessor for password
	 * @return String password
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Mutator for emailAddress
	 * @param emailAddress the email address of the user attempting to log in
	 */
	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Mutator for password
	 * @param password the password that was provided by the user attempting to log in
	 */
	public void setPassword(String password){
		this.password = password;
	}
}
