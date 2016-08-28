package ie.wit.repository;

import ie.wit.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by joewe on 28/08/2016.
 */
public interface RoleRepo extends JpaRepository<Role, Long>
{
	Role findByName(String name);
}
