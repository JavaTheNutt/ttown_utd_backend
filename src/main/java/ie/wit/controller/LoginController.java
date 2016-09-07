package ie.wit.controller;

import ie.wit.model.dto.in.LoginDto;
import ie.wit.model.dto.out.UserOutDto;
import ie.wit.model.dto.temp_transfer.UserJwtTransfer;
import ie.wit.service.access.LoginService;
import ie.wit.service.util.response.ResponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	 * Logger for this class
	 */
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Autowired reference to the login service
	 */
	private LoginService loginService;

	/**
	 * Autowired reference to the response service
	 */
	private ResponseService responseService;

	/**
	 * Autowired constructor
	 *
	 * @param loginService    the login service
	 * @param responseService the response service
	 */
	@Autowired
	public LoginController(LoginService loginService, ResponseService responseService)
	{
		this.loginService = loginService;
		this.responseService = responseService;
	}

	/**
	 * The endpoint that will be used to login
	 *
	 * @param loginDetails valid login details of a user
	 * @return a response entity
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserOutDto> login(@RequestBody @Valid LoginDto loginDetails)
	{
		logger.info("Post to login controller received");
		UserJwtTransfer userTransfer = loginService.login(loginDetails);
		HttpHeaders headers = responseService.adjustHeaders(userTransfer.getJwt());
		logger.info("returning user: " + userTransfer.getUser().toString());
		return new ResponseEntity<>(userTransfer.getUser(), headers, HttpStatus.OK);
	}
}
