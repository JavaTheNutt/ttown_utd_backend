package ie.wit.controller;

import ie.wit.model.dto.in.LoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This will be the only unsecured endpoint.
 * Used to login to the system and return a JWT in the header for future Authentication
 *
 * @author Joe Wemyss
 */
@RestController
@RequestMapping("/login")
public class LoginController
{
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	//TODO: refactor this to return the details of the user that is logged in.
	//TODO: create a service for building response entities??
	/**
	 * The endpoint that will be used to login
	 *
	 * @param headers      the http headers os that a JWT can be appended to them
	 * @param loginDetails valid login details of a user
	 * @return a response entity
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestHeader HttpHeaders headers, @RequestBody @Valid LoginDto loginDetails)
	{
		logger.info("Post to login controller received");
		// FIXME: 25/08/2016 Implement logic for login
		//1. post user details to login service
		//2. receive the details of the user that logged in as well as a JWT for future authentication
		//3. return a ResponseEntity containing the user details in the body, and the jwt in the header(where should this come from??)
		return new ResponseEntity<String>("Hello client", HttpStatus.OK);
	}
}
