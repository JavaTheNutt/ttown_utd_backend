package ie.wit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Exception will be thrown if a user requested is not found in the database
 *
 * @author Joe Wemyss
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends RuntimeException
{
	public UserNotFoundException()
	{
		super("User not found");
	}
}
