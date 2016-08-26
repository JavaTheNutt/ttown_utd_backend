package ie.wit.service;

import ie.wit.model.entity.UserEntity;
import ie.wit.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class will act as the intermediary between the other classes and the {@link ie.wit.repository.UserRepo}
 *
 * @author Joe Wemyss
 * @see ie.wit.model.entity.UserEntity
 */
@Service

public class UserService
{
	/**
	 * The logger for this class
	 */
	Logger logger = LoggerFactory.getLogger(UserService.class);
	/**
	 * The reference to the user repository
	 */
	private UserRepo userRepo;

	/**
	 * Constructor.
	 *
	 * @param userRepo the user repository
	 */
	@Autowired
	public UserService(UserRepo userRepo)
	{
		this.userRepo = userRepo;
	}

	/**
	 * Return all of the users.
	 *
	 * @return  the list of users
	 */
	public List<UserEntity> getUsers()
	{
		return userRepo.findAll();
	}
	
	/**
	 * Return a single user based on their email address.
	 * 
	 * @param emailAddress  the users email address, which is unique in the database
	 * @return  the requested user
	 * @throws  an exception if the user is not found
	 */
	public UserEntity getOneUserByEmail(String emailAddress){
		UserEntity user = userRepo.findByEmailAddress();
		if(user == null){
			logger.error("User with the email address " + emailAddress + " not found!");
			throw new UserNotFoundException();
		}
		logger.info("User with the email address " + emailAddress + " found successfully");
		return user;
	}

}
