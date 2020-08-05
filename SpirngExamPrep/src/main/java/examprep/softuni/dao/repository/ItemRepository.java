package examprep.softuni.dao.repository;

import examprep.softuni.dao.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,String> {

}
