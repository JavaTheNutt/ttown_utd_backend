package ie.wit.exceptions;

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
  
  	/**
  	 * Handle UserNotFoundException
  	 * 
  	 * @param req  the request that triggered the exception
  	 * @param e  the exception that was thrown
  	 * @return  a response entity containing the exception
  	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ClientErrorInformation> handleUserNotFound(HttpServletRequest req, Exception e){
    	return handleUnauthorized(req, e);
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<ClientErrorInformation> handlePasswordMismatch(HttpServletRequest req, Exception e){
		return handleUnauthorized(req, e);
	}
	
	/**
	 * Handle any exception that needs to throw a Status code 401.
	 * 
	 * @param req  the request that triggered the exception
  	 * @param e  the exception that was thrown
  	 * @return  a response entity containing the exception
	 */
	private ResponseEntity<ClientErrorInformation> handleUnauthorized(HttpServletRequest req, Exception e){
		return handleException(req, e, HttpStatus.UNAUTHORIZED);
	}
	/**
	 * Handle any exception.
	 * 
	 * @param req  the request that triggered the exception
  	 * @param e  the exception that was thrown
  	 * @param status  the status to be returned
  	 * @return  a response entity containing the exception
	 */
	private ResponseEntity<ClientErrorInformation> handleException(HttpServletRequest req, Exception e, HttpStatus status){
		ClientErrorInformation error = new ClientErrorInformation(e, req.getRequestURI());
    	return new ResponseEntity<>(error, status);
	}
}
