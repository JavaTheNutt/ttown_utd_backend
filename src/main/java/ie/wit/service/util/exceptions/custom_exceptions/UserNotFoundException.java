package ie.wit.service.util.exceptions.custom_exceptions;

/**
 * This Exception will be thrown if a user requested is not found in the database
 *
 * @author Joe Wemyss
 */
public class UserNotFoundException extends RuntimeException
{
	/**
	 * {@inheritDoc}
	 */
	public UserNotFoundException()
	{
		super("User not found");
	}

	/**
	 * {@inheritDoc}
	 */
	public UserNotFoundException(String message)
	{
		super(message);
	}
}
