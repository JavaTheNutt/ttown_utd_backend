package ie.wit.service.util.response;

import ie.wit.service.access.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for generating responses for the client.
 *
 * @author Joe Wemyss
 */
@Service
public class ResponseService
{
	private LoginService loginService;

	@Autowired
	public ResponseService(LoginService loginService)
	{
		this.loginService = loginService;
	}

	/**
	 * This method will create a new set of http headers containing the JWT for authentication and set the length to the length of the content + 10.
	 *
	 * @param oldJwt the old jwt last given by the system
	 * @return a set of HttpHeaders
	 */
	public HttpHeaders fetchAdminHeaders(String oldJwt)
	{
		String jwt = loginService.fetchAdminJwt(oldJwt);
		return createHeaders(jwt);
	}
	public HttpHeaders fetchWriteHeaders(String oldJwt){
		String jwt = loginService.fetchWriteJwt(oldJwt);
		return createHeaders(jwt);
	}
	public HttpHeaders fetchReadHeaders(String oldJwt){
		String jwt = loginService.fetchReadJwt(oldJwt);
		return createHeaders(jwt);
	}
	private HttpHeaders createHeaders(String newJwt){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("auth", newJwt);
		return headers;
	}

}
