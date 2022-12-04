package PP_3_1_4.repositories;

import PP_3_1_4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u join fetch u.roles where u.username = :username")
    Optional<User> findByUsername(String username);
    User findUserByUsername(String username);

}
