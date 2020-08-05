package softuni.springexam.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.springexam.dao.entity.User;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
