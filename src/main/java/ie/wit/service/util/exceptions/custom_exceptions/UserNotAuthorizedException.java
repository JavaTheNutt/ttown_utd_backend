package ie.wit.service.util.exceptions.custom_exceptions;

/**
 * This exception is thrown when a user is not authorized
 */
public class UserNotAuthorizedException extends RuntimeException
{
	/**
	 * {@inheritDoc}
	 */
	public UserNotAuthorizedException()
	{
		super("The user is not authorized");
	}
}
