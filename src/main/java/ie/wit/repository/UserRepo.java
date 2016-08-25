package ie.wit.repository;

import ie.wit.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for UserEntity
 * @see ie.wit.model.entity.UserEntity UserEntity
 * @author Joe Wemyss
 */
public interface UserRepo extends JpaRepository<UserEntity, Long>
{
  /**
   * Find a user by their email address. Will only return one as email address must be unique
   * @param String the email address
   * @return Optional<UserEntity> Nullable user.
   */
  Optional<UserEntity> findByEmailAddress(String emailAddress);
}
