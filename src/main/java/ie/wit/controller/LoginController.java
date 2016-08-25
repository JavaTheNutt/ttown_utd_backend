package ie.wit.controller;

import ie.wit.model.dto.in.LoginDto;
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
		// FIXME: 25/08/2016 Implement logic for login
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
