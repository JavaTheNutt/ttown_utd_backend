package ie.wit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service will validate user logins and defer to another service to create the JWT for authentication
 *
 * @author Joe Wemyss
 */
@Service
public class LoginService
{
	private Logger logger = LoggerFactory.getLogger(LoginService.class);
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


}
