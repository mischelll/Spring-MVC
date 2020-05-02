package springworkshop.demo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springworkshop.demo.data.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByAndAuthority(String authority);
}
