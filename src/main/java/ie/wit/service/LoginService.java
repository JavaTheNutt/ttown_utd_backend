package ie.wit.service;

import ie.wit.model.dto.in.LoginDto;
import ie.wit.model.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * This service will validate user logins and defer to another service to create the JWT for authentication
 *
 * @author Joe Wemyss
 */
@Service
public class LoginService
{
	/**
	 * Logger for this class
	 */
	private Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Value("${jwt.secret.name}")
	private String secret;

	/**
	 * Autowired instance of the userService.
	 */
	private UserService userService;

	/**
	 * Constructor.
	 *
	 * @param userService instance of the {@link UserService}
	 */
	@Autowired
	public LoginService(UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * Take login details and verify.
	 *
	 * @param loginDetails the users login details to be verified
	 * @return A JWT to be appended to the response header
	 */
	public String login(LoginDto loginDetails)
	{
		logger.debug("request received by LoginService.login() for " + loginDetails.getEmailAddress());
		// FIXME: implement logic to validate loginDetails and make a call to a service to create a JWT
		return "Enter JWT Here!!";
	}

	/**
	 * Validate if the users details are correct.
	 *
	 * @param loginDetails the login details passed by the client
	 * @return a boolean to denote if the login details are correct
	 */
	private boolean userValid(LoginDto loginDetails)
	{
		//get the user from the database.

		//compare the two password and return the result.
		return false;
	}


	/**
	 * Get a user based on their email address.
	 *
	 * @param emailAddress the users email address
	 * @return the user
	 */
	private UserEntity getUser(String emailAddress)
	{
		return userService.getOneUserByEmail(emailAddress);
	}
}
