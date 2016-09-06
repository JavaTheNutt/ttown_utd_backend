package ie.wit.service.response;

import ie.wit.model.dto.out.IOut;
import ie.wit.model.dto.out.UserOutDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * This class is responsible for generating responses for the client.
 */
public class ResponseService
{
	public ResponseEntity<UserOutDto> requestResponse(UserOutDto objectOut, HttpHeaders headers, String jwt){
		// FIXME: 06/09/2016 get this to return the correct reponse entity
		return null;
	}
	protected ResponseEntity<IOut> createResponse(IOut objectOut, HttpHeaders headers, String jwt){
		headers.add("auth", jwt);
		headers.set("content-length", Integer.toString(objectOut.toString().length() + 10));

		HttpHeaders headersOut = adjustHeaders(jwt, objectOut.toString().length());
		return new ResponseEntity<IOut>(objectOut, headers, HttpStatus.OK);
	}
	private HttpHeaders adjustHeaders(String jwt, int bodyLength){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("auth", jwt);
		headers.setContentLength(bodyLength + 10);
		return headers;
		
	}
}
