package ie.wit.service.access;

import ie.wit.model.dto.in.LoginDto;
import ie.wit.model.dto.out.UserOutDto;
import ie.wit.model.dto.temp_transfer.UserJwtTransfer;
import ie.wit.model.entity.UserEntity;
import ie.wit.model.enums.Role;
import ie.wit.service.util.exceptions.custom_exceptions.PasswordMismatchException;
import ie.wit.service.util.exceptions.custom_exceptions.UserNotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

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

	/**
	 * Take login details and verify.
	 *
	 * @param loginDetails the users login details to be verified
	 * @return A JWT to be appended to the response header
	 */
	public UserJwtTransfer login(LoginDto loginDetails)
	{
		logger.debug("request received by LoginService.login() for " + loginDetails.getEmailAddress());

		UserEntity user = userService.getOneUserByEmail(loginDetails.getEmailAddress());

		if (!userValid(user, loginDetails.getPassword())) {
			throw new UserNotAuthorizedException();
		}
		String role = getUsersRole(user);
		String jwt = jwtService.requestJwt(user.getEmailAddress(), role);
		return new UserJwtTransfer(jwt, new UserOutDto(user));

	}

	/**
	 * This returns the name of the users role based on the integer value.
	 * 
	 * @param user the user whos role name is required
	 * @return the name of the users role
	 */
	private String getUsersRole(UserEntity user)
	{
		return Role.getStringValueFromInt(user.getRole());
	}

	//todo: delete this method if testing the other one is unsuccessful
	/**
	 * Validate a passed JWT and return a new one.
	 *
	 * @param details a map containing the original jwt, the users email address and the users role
	 * @return a jwt if the original was valid, "Not Authorized" otherwise.
	 */
	public String validateAndRegenerateJwt(Map<String, String> details)
	{
		//todo: refactor this as the required details are already contained in the JWT. This should be able to request a new JWT using the information contained in the old one.
		if (validateJwt(details.get("jwt"))) {
			return jwtService.requestJwt(details.get("emailAddress"), details.get("role"));
		}
		return "Not Authorized";
	}
	
	/**
	 * Validate a passed jwt and return a new one
	 * 
	 * @param jwt the old JWT
	 * @return a new JWT
	 */
	public String validateAndRegenerateJwt(String jwt){
		if(validateJwt(jwt){
			Map<String, String> details = jwtService.getUsernameAndRole(jwt);
			return jwtService.requestJwt(details.get("user"), details.get("role");
		}
		return "Not Authorized";
	}
	/**
	 * This will return a user based on the jwt passed
	 * 
	 * @param jwt the JWT sent in the header of the request
	 * @return the user who was given the JWT
	 */
	public UserEntity getUserFromJwt(String jwt){
		//1. parse jwt.
		//2. get the user contained in the jwt 
		//3. return the user
	}
	
	/**
	 * This method will take a JWT in String form and will validate whether the user that sent it is an admin.
	 *
	 * @param jwt the jwt to be validated
	 * @return true if the jwt specifies that the user is an admin, false otherwise
	 */
	// TODO: should this return a new JWT rather than a boolean? Perhaps another method should generate the JWT when this one returns true.
	//TODO: make this method private, and have another public method which requires a user name and role. so that when the user posts a JWT, they get a new one in return
	//TODO: or refactor the jwt service so that it returns the data from when it validates the original jwt
	boolean validateJwt(String jwt)
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

}
