package ie.wit.service.util.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for generating responses for the client.
 *
 * @author Joe Wemyss
 */
@Service
public class ResponseService
{
	/**
	 * This method will create a new set of http headers containing the JWT for authentication and set the length to the length of the content + 10.
	 *
	 * @param jwt        the JWT to be added to the headers
	 * @param bodyLength the length of the body of the response
	 * @return a set of HttpHeaders
	 */
	public HttpHeaders adjustHeaders(String jwt, int bodyLength)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("auth", jwt);
		headers.setContentLength(bodyLength + 10);
		return headers;

	}
}
