package ie.wit.service.util.exceptions.handle;

import ie.wit.service.util.exceptions.custom_exceptions.PasswordMismatchException;
import ie.wit.service.util.exceptions.custom_exceptions.UserNotFoundException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.MissingClaimException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * This class handles exceptions, wraps them in a ResponseEntity and returns them to the client
 *
 * @author Joe Wemyss
 */
public class HandleException
{
	private Logger logger = LoggerFactory.getLogger(HandleException.class);

	/**
	 * Handle UserNotFoundException
	 *
	 * @param req the request that triggered the exception
	 * @param e   the exception that was thrown
	 * @return a response entity containing the exception
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ClientErrorInformation> handleUserNotFound(HttpServletRequest req, Exception e)
	{
		logger.debug("Warning, UserNotFoundException thrown. ");
		return handleUnauthorized(req, e);
	}

	/**
	 * Handle PasswordMismatchException.
	 *
	 * @param req the request that triggered the exception
	 * @param e   the exception that was thrown
	 * @return a response entity containing the exception
	 */
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<ClientErrorInformation> handlePasswordMismatch(HttpServletRequest req, Exception e)
	{
		logger.debug("Warning, PasswordMismatchException thrown. ");
		return handleUnauthorized(req, e);
	}

	/**
	 * Handle InvalidClaimException.
	 *
	 * @param req the request that triggered the exception
	 * @param e   the exception that was thrown
	 * @return a response entity containing the exception
	 */
	@ExceptionHandler(InvalidClaimException.class)
	public ResponseEntity<ClientErrorInformation> handleInvalidClaim(HttpServletRequest req, Exception e)
	{
		logger.debug("Warning, InvalidClaimException thrown. ");
		return handleUnauthorized(req, e);
	}
	/**
	 * Handle MissingClaimException.
	 *
	 * @param req the request that triggered the exception
	 * @param e   the exception that was thrown
	 * @return a response entity containing the exception
	 */
	@ExceptionHandler(MissingClaimException.class)
	public ResponseEntity<ClientErrorInformation> handleMissingClaim(HttpServletRequest req, Exception e){
		logger.debug("Warning, MissingClaimException thrown. ");
		return handleUnauthorized(req, e);
	}

	/**
	 * General exception handler.
	 *
	 * @param req the request that triggered the exception
	 * @param e   the exception that was thrown
	 * @return a response entity containing the exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ClientErrorInformation> handleException(HttpServletRequest req, Exception e)
	{
		logger.debug("Warning, an unknown error has occured");
		return handleInternalServerError(req, e);
	}

	/**
	 * Handle any exception that needs to throw a status code 401.
	 *
	 * @param req the request that triggered the exception
	 * @param e   the exception that was thrown
	 * @return a response entity containing the exception
	 */
	private ResponseEntity<ClientErrorInformation> handleUnauthorized(HttpServletRequest req, Exception e)
	{
		logger.debug("Returning a 401 status to the client");
		return handleException(req, e, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handle any exception that needs to throw a status code 500.
	 *
	 * @param req the request that triggered the exception
	 * @param e   the exception that was thrown
	 * @return a response entity containing the exception
	 */
	private ResponseEntity<ClientErrorInformation> handleInternalServerError(HttpServletRequest req, Exception e)
	{
		logger.debug("Returning a 500 status code to the client");
		return handleException(req, e, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle any exception.
	 *
	 * @param req    the request that triggered the exception
	 * @param e      the exception that was thrown
	 * @param status the status to be returned
	 * @return a response entity containing the exception
	 */
	private ResponseEntity<ClientErrorInformation> handleException(HttpServletRequest req, Exception e, HttpStatus status)
	{
		ClientErrorInformation error = new ClientErrorInformation(e, req.getRequestURI());
		logger.error("An error has occured at " + error.getUri() + "\nMessage: " + error.getMessage());
		return new ResponseEntity<>(error, status);
	}
}
