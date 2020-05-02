package springworkshop.demo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springworkshop.demo.data.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findByUsername(String username);
}
