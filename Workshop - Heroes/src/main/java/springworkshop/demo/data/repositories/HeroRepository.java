package springworkshop.demo.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springworkshop.demo.data.models.Hero;

import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, String> {
   Optional<Hero> findByName(String name);
}
