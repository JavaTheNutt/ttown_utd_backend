package ie.wit.model.dto.temp_transfer;

import ie.wit.model.dto.out.UserOutDto;

/**
 * This class holds a JWT in string form as well as the user that it was issued to.
 *
 * @author Joe Wemyss
 */
public class UserJwtTransfer
{
	/**
	 * The JWT to be used for authentication.
	 */
	private final String jwt;

	/**
	 * The user that logged in.
	 */
	private final UserOutDto user;

	/**
	 * Constructor.
	 *
	 * @param jwt  the JWT to be used for authentication
	 * @param user the user that logged in
	 */
	public UserJwtTransfer(String jwt, UserOutDto user)
	{
		this.jwt = jwt;
		this.user = user;
	}

	/**
	 * Accessor for the JWT.
	 *
	 * @return the JWT
	 */
	public String getJwt()
	{
		return jwt;
	}

	/**
	 * Accessor for the user.
	 *
	 * @return the user
	 */
	public UserOutDto getUser()
	{
		return user;
	}
}
