package ie.wit.service.response;

import ie.wit.model.dto.out.IOut;
import ie.wit.model.dto.out.UserOutDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * This class is responsible for generating responses for the client.
 * 
 * @author Joe Wemyss
 */
public class ResponseService
{
	//todo: is this the correct way to appraoch this? Should this service be injected into each entity service so that they can validate the jwts themselves and then only call this service if needed?
	//todo: create a generic method that will be able to create any resoponse object so that there can be an endpoint for each type of object, with the functionality contained in more generic methods.
	//todo: remove the headers from the signiture as this class will no longer validate the jwt
	/**
	 * Return user details to the client.
	 * 
	 * @param objectOut the user details to be returned to the client
	 * @param headers the http headers for the incoming request, to validate that they contain a valid JWT.
	 * @param jwt the new JWT to be sent to the user in the headers
	 */
	public ResponseEntity<UserOutDto> requestResponse(UserOutDto objectOut, HttpHeaders headers, String jwt){
		// FIXME: 06/09/2016 get this to return the correct reponse entity
		return null;
	}
	/**
	 * This is a generic method that can return a response entity containing any of the outward bound data transfer object.
	 * 
	 * @param objectOut the object to be transferred 
	 * @param headers the headers of the request
	 * @param jwt the JWT to be added to the headers
	 */
	protected ResponseEntity<IOut> createResponse(IOut objectOut, HttpHeaders headers, String jwt){
		HttpHeaders headersOut = adjustHeaders(jwt, objectOut.toString().length());
		return new ResponseEntity<IOut>(objectOut, headers, HttpStatus.OK);
	}
	
	/**
	 * This method will create a new set of http headers containing the JWT for authentication and set the length to the length of the content + 10.
	 * 
	 * @param jwt the JWT to be added to the headers
	 * @param bodyLength the length of the body of the response.
	 */
	public HttpHeaders adjustHeaders(String jwt, int bodyLength){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("auth", jwt);
		headers.setContentLength(bodyLength + 10);
		return headers;
		
	}
}
