package ie.wit.service.util.exceptions.custom_exceptions;

/**
 * This class represents the exception that will be thrown if the password a user provides does not match the expected.
 *
 * @author Joe Wemyss
 */
public class PasswordMismatchException extends RuntimeException
{
	/**
	 * {@inheritDoc}
	 */
	public PasswordMismatchException()
	{
		super("The passwords do not match");
	}
}
