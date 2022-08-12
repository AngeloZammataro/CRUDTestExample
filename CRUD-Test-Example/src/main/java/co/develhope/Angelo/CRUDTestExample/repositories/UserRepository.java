package co.develhope.Angelo.CRUDTestExample.repositories;

import co.develhope.Angelo.CRUDTestExample.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
