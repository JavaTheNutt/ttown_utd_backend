package ie.wit.service.access;

import ie.wit.service.util.exceptions.custom_exceptions.UserNotFoundException;
import ie.wit.model.entity.UserEntity;
import ie.wit.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.transaction.Transactional;
import java.util.List;

/**
 * This class will act as the intermediary between the other classes and the {@link ie.wit.repository.UserRepo}
 *
 * @author Joe Wemyss
 * @see ie.wit.model.entity.UserEntity
 */
@Service
@Transactional
class UserService
{
	/**
	 * The logger for this class
	 */
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	/**
	 * The reference to the user repository
	 */
	private UserRepo userRepo;

	/**
	 * The reference to the hasing service
	 */
	private HashingService hashingService;

	/**
	 * Constructor.
	 *
	 * @param userRepo the user repository
	 */
	@Autowired
	 UserService(UserRepo userRepo, HashingService hashingService)
	{
		this.userRepo = userRepo;
		this.hashingService = hashingService;
	}

	/**
	 * Return all of the users.
	 *
	 * @return the list of users
	 */
	 List<UserEntity> getUsers()
	{
		logger.info("UserService#getUsers called. " + userRepo.findAll().size() + " records found." );
		return userRepo.findAll();
	}

	/**
	 * Return a single user based on their email address.
	 *
	 * @param emailAddress the users email address, which is unique in the database
	 * @return the requested user
	 * @throws UserNotFoundException exception if the user is not found
	 */
	 UserEntity getOneUserByEmail(String emailAddress)
	{
		UserEntity user = userRepo.findByEmailAddress(emailAddress);
		if (user == null) {
			logger.error("UserService#getOneByEmail() with email" + emailAddress + " not found!!");
			throw new UserNotFoundException();
		}
		logger.info("User with the email address " + emailAddress + " found successfully");
		return user;
	}

	/**
	 * Add a user to the database.
	 *
	 * @param user the user to be added
	 * @return a copy of the user that was added to the database
	 */
	UserEntity addUser(UserEntity user){
		logger.debug("request recieved to add user");
		user.setPassword(hashingService.generatePasswordHash(user.getPassword()));
		return userRepo.saveAndFlush(user);
	}

	/**
	 * This method will delete the user with the specified email address.
	 *
	 * @param emailAddress the email address of the user to be deleted
	 */
	@Transactional
	 void deleteUser(String emailAddress){
		logger.debug("Request received by delete user service");
		UserEntity user = getOneUserByEmail(emailAddress);
		userRepo.delete(user);
	}

}
