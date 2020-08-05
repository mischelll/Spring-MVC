package examprep.softuni.dao.repository;

import examprep.softuni.dao.entity.Category;
import examprep.softuni.dao.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
    Category findByName(CategoryName name);

}
