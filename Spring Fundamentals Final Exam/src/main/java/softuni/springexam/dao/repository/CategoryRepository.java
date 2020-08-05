package softuni.springexam.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.springexam.dao.entity.Category;
import softuni.springexam.dao.entity.CategoryName;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(CategoryName name);
}
