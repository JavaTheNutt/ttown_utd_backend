package ie.wit.service.util.exceptions.custom_exceptions;

/**
 * This is the exception that is thrown when a passed JWT is invalid
 * 
 * @author Joe Wemyss
 */
public class InvalidJwtException extends RuntimeException
{
	/**
	 * {@inheritDoc}
	 */
	 @Override
	public InvalidJwtException()
	{
		super("Invalid JWT! The JWT passed is not valid!");
	}
	
	/**
	 * {@inheritDoc}
	 */
	 @Override
	public InvalidJwtException(String message){
		super(message);
	}
}
