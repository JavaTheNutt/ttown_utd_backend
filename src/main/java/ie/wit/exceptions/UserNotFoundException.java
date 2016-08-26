package ie.wit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Exception will be thrown if a user requested is not found in the database
 *
 * @author Joe Wemyss
 */
public class UserNotFoundException extends RuntimeException
{
	/**
	 * Default constructor with predefined message
	 */
	public UserNotFoundException()
	{
		super("User not found");
	}
	
	/**
	 * Constructor with message specified by the caller
	 */
	public UserNotFoundException(String message){
		super(message);
	}
}
