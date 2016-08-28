package ie.wit.service.util.exceptions.handle;

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
	 *
	 * @param e   The Exception that was thrown
	 * @param uri The request uri that caused the exception
	 */
	public ClientErrorInformation(Exception e, String uri)
	{
		this.exception = e;
		this.message = createErrorMessage(e);
		this.uri = uri;
	}

	// TODO: 27/08/2016 Format error message correctly

	/**
	 * Create the message.
	 *
	 * @param e The exception that was thrown
	 * @return The message
	 */
	private String createErrorMessage(Exception e)
	{
		return e.toString();
	}

	/**
	 * Accessor for message
	 *
	 * @return the message.
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Mutator for message.
	 *
	 * @param message the message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Accessor for uri.
	 *
	 * @return the uri of the request that caused the exception
	 */
	public String getUri()
	{
		return uri;
	}

	/**
	 * Mutator for message.
	 *
	 * @param uri the uri of the request that caused the esception
	 */
	public void setUri(String uri)
	{
		this.uri = uri;
	}

	/**
	 * Simple toString() method.
	 *
	 * @return The class in string format
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
