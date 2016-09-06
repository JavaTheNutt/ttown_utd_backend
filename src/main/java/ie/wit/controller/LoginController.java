package ie.wit.controller;

import ie.wit.model.dto.in.LoginDto;
import ie.wit.model.dto.out.UserOutDto;
import ie.wit.model.dto.temp_transfer.UserJwtTransfer;
import ie.wit.service.access.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	private LoginService loginService;
	private ResponseService responseService;

	@Autowired
	public LoginController(LoginService loginService, ResponseService responseService)
	{
		this.loginService = loginService;
		this.responseService = responseService;
	}

	/**
	 * The endpoint that will be used to login
	 *
	 * @param headers      the http headers os that a JWT can be appended to them
	 * @param loginDetails valid login details of a user
	 * @return a response entity
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserOutDto> login(@RequestHeader HttpHeaders headers, @RequestBody @Valid LoginDto loginDetails)
	{
		//todo: remove the headers from the signiture and use the response service to generate the response
		logger.info("Post to login controller received");
		UserJwtTransfer userTransfer = loginService.login(loginDetails);
		headers.add("auth", userTransfer.getJwt());
		logger.info("The headers: " + headers);
		headers.set("content-length", "200");
		logger.info("returning user: " + userTransfer.getUser().toString());
		// TODO: 03/09/2016 create a service that can be used to create response objects that can be used 
		return new ResponseEntity<>(userTransfer.getUser(), headers, HttpStatus.OK);
	}
	
	/**
	 * The endpoint that will be used to login
	 *
	 * @param headers      the http headers os that a JWT can be appended to them
	 * @param loginDetails valid login details of a user
	 * @return a response entity
	 */
	/*@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserOutDto> login(@RequestBody @Valid LoginDto loginDetails)
	{
		logger.info("Post to login controller received");
		UserJwtTransfer userTransfer = loginService.login(loginDetails);
		HttpHeaders headers = responseService.adjustHeaders(userTransfer.getJwt(), userTransfer.getUser().toString().length);
		logger.info("returning user: " + userTransfer.getUser().toString());
		// TODO: 03/09/2016 create a service that can be used to create response objects that can be used 
		return new ResponseEntity<>(userTransfer.getUser(), headers, HttpStatus.OK);
	}*/
}
