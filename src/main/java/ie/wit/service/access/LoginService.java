package ie.wit.service.access;

import ie.wit.model.dto.in.LoginDto;
import ie.wit.model.entity.UserEntity;
import ie.wit.model.enums.Role;
import ie.wit.service.util.exceptions.custom_exceptions.PasswordMismatchException;
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
	 * @param userService    instance of the {@link UserService}
	 * @param hashingService instance of the {@link HashingService}
	 * @param jwtService     instance of {@link JwtService}
	 */
	@Autowired
	public LoginService(UserService userService, HashingService hashingService, JwtService jwtService)
	{
		this.userService = userService;
		this.hashingService = hashingService;
		this.jwtService = jwtService;
	}


	//TODO: This should return a UserOutDto as well as a String for the client session. Perhaps in Map<String, UserOutDto> where the String is the JWT??

	/**
	 * Take login details and verify.
	 *
	 * @param loginDetails the users login details to be verified
	 * @return A JWT to be appended to the response header
	 */
	public String login(LoginDto loginDetails)
	{
		logger.debug("request received by LoginService.login() for " + loginDetails.getEmailAddress());
		UserEntity user = getUser(loginDetails.getEmailAddress());

		//todo: test the below to see if the other method is superflous.
		//UserEntity user = userService.getOneByEmailAddress(loginDetails.getEmailAddress());

		// FIXME: implement logic to validate loginDetails and make a call to a service to create a JWT
		// FIXME: edit JWT service to accept a collection of roles instead of just one
		// FIXME: this needs to return a reference to the user as well as the JWT. Perhaps a new Data Structure, or a Map?

		String role = getUsersRole(user);
		////return userValid(user, loginDetails.getPassword()) ? jwtService.requestJwt(user.getEmailAddress(), user.getRoles().get(0).getName()) : "Not Authorized";
		//TODO: remove above line and uncomment below line when user entity is refactored.
		return userValid(user, loginDetails.getPassword()) ? jwtService.requestJwt(user.getEmailAddress(), role) : "Not Authorized";
	}

	String getUsersRole(UserEntity user)
	{
		return Role.getStringValueFromInt(user.getRole());
	}
	/**
	 * Validate a passed JWT and return a new one.
	 *
	 * @param details a map containing the original jwt, the users email address and the users role
	 * @return a jwt if the original was valid, "Not Authorized" otherwise.
	 */
	/*public String validateAndRegenerateJwt(Map<String, String> details){
		if(validateJwt(details.get("jwt"))){
			return jwtService.requestJwt(details.get("emailAddress"), details.get("role"));
		}
		return "Not Authorized";
	}*/

	/**
	 * This method will take a JWT in String form and will validate whether the user that sent it is an admin.
	 *
	 * @param jwt the jwt to be validated
	 * @return true if the jwt specifies that the user is an admin, false otherwise
	 */
	// TODO: should this return a new JWT rather than a boolean? Perhaps another method should generate the JWT when this one returns true.
	//TODO: make this method private, and have another public method which requires a user name and role. so that when the user posts a JWT, they get a new one in return
	//TODO: or refactor the jwt service so that it returns the data from when it validates the original jwt
	public boolean validateJwt(String jwt)
	{
		logger.debug("Login service checking the validity of the JWT");
		return jwtService.validateAdmin(jwt);
	}

	/**
	 * Validate if the users details are correct.
	 *
	 * @param user      the user retrieved from the database
	 * @param plaintext the plaintext password to be compared
	 * @return a boolean to denote if the login details are correct
	 */
	private boolean userValid(UserEntity user, String plaintext)
	{
		logger.debug("Login service validating user");
		if (!hashingService.checkPassword(plaintext, user.getPassword())) {
			logger.error("The password passed in did not match!");
			throw new PasswordMismatchException();
		}
		logger.debug("The password matches and the user is valid");
		return true;
	}

	/**
	 * Get a user based on their email address.
	 *
	 * @param emailAddress the users email address
	 * @return the user
	 */
	// TODO: Remove this? Unessesary? It is only a proxy call to the user service.
	private UserEntity getUser(String emailAddress)
	{
		logger.debug("Login service retrieving user with email address: " + emailAddress);
		return userService.getOneUserByEmail(emailAddress);
	}
}
