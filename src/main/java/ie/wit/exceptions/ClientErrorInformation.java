package ie.wit.exceptions;

/**
 * This class will be a wrapper for that will contain information about exceptions.
 *
 * @author Joe Wemyss
 */
public class ClientErrorInformation
{
	/**
	 * The message that will be output.
	 * Generated from the exception.
	 */
	private String message;
	/**
	 * The Exception that was thrown.
	 */
	private Exception exception;
	/**
	 * The request uri that caused the exception.
	 */
	private String uri;

	/**
	 * Constructor.
	 * @param e  The Exception that was thrown
	 * @param uri  The request uri that caused the exception
	 */
	public ClientErrorInformation(Exception e, String uri){
		this.exception = e;
		this.message = createErrorMessage(e);
		this.uri = uri;
	}

	/**
	 * Create the message.
	 * @param e  The exception that was thrown
	 * @return  The message
	 */
	private String createErrorMessage(Exception e){
		return e.toString();
	}

	/**
	 * Simple toString() method.
	 * @return  The class in string form
	 */
	@Override
	public String toString()
	{
		return "ClientErrorInformation{" +
				"message='" + message + '\'' +
				", uri='" + uri + '\'' +
				'}';
	}
}
