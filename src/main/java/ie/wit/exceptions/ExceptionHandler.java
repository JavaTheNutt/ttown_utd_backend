package ie.wit.exceptions
/**
 * This class handles exceptions, wraps them in a ResponseEntity and returns them to the client
 * 
 * @author Joe Wemyss
 */
public class ExceptionHandler{
  
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ClientErrorInformation> handleUserNotFound(HttpServletRequest req, Exception e){
    	ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
    	return new ResponseEntity<ClientErrorInformation>(error, HttpStatus.UNAUTHORIZED);
	}
}
