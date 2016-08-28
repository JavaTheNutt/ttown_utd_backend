package ie.wit.repository;

import ie.wit.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for UserEntity
 *
 * @author Joe Wemyss
 * @see ie.wit.model.entity.UserEntity UserEntity
 */
public interface UserRepo extends JpaRepository<UserEntity, Long>
{
	/**
	 * Find a user by their email address. Will only return one as email address must be unique
	 *
	 * @param emailAddress the email address
	 * @return Nullable user.
	 */
	UserEntity findByEmailAddress(String emailAddress);

}
