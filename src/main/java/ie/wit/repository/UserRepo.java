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
}
