package ie.wit.repository;

import ie.wit.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link UserEntity}
 *
 * @author Joe Wemyss
 */
@Repository
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
