package ie.wit.service.access;

import ie.wit.service.util.exceptions.custom_exceptions.PasswordMismatchException;
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
	 * Autowired reference to the hashing service.
	 */
	private HashingService hashingService;

	/**
	 * Autowired reference to the jwt service.
	 */
	private JwtService jwtService;

	/**
	 * Constructor.
	 *
	 * @param userService  instance of the {@link UserService}
	 * @param hashingService  instance of the {@link HashingService}
	 * @param jwtService  instance of {@link JwtService}   
	 */
	@Autowired
	public LoginService(UserService userService, HashingService hashingService, JwtService jwtService)
	{
		this.userService = userService;
		this.hashingService = hashingService;
		this.jwtService = jwtService;
	}

	/**
	 * Take login details and verify.
	 *
	 * @param loginDetails the users login details to be verified
	 * @return A JWT to be appended to the response header
	 */
	public String login(LoginDto loginDetails)
	{
		// TODO: 28/08/2016 TEST!!!!!!!!!! 
		logger.debug("request received by LoginService.login() for " + loginDetails.getEmailAddress());
		UserEntity user = getUser(loginDetails.getEmailAddress());
		// FIXME: implement logic to validate loginDetails and make a call to a service to create a JWT
		return userValid(user) ? jwtService.requestJwt(user.getEmailAddress(), user.getRoles().get(0).getName()): "Not Authorized";
	}

	/**
	 * Validate if the users details are correct.
	 *
	 * @param user the login details passed by the client
	 * @return a boolean to denote if the login details are correct
	 */
	private boolean userValid(UserEntity user)
	{
		if(!hashingService.checkPassword(user.getPassword(), user.getPassword())){
			throw new PasswordMismatchException();
		}
		return true;
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