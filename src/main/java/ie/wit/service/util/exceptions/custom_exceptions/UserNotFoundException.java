package ie.wit.service.util.exceptions.custom_exceptions;

/**
 * This Exception will be thrown if a user requested is not found in the database
 *
 * @author Joe Wemyss
 */
public class UserNotFoundException extends RuntimeException
{
	/**
	 * Default constructor with predefined message.
	 */
	public UserNotFoundException()
	{
		super("User not found");
	}

	/**
	 * Constructor with message specified by the caller.
	 *
	 * @param message the message to be associated with the exception
	 */
	public UserNotFoundException(String message)
	{
		super(message);
	}
}
