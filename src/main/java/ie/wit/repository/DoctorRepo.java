package ie.wit.repository;

import ie.wit.model.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for {@link ie.wit.model.entity.DoctorEntity}
 *
 * @author Joe Wemyss
 */
@Repository
public interface DoctorRepo extends JpaRepository<DoctorEntity, Long>
{

}
