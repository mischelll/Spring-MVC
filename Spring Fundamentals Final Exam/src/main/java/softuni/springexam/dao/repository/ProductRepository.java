package softuni.springexam.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.springexam.dao.entity.Category;
import softuni.springexam.dao.entity.CategoryName;
import softuni.springexam.dao.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByName(String name);

    List<Product> findByCategory(Category category);

    void deleteById(String id);
}
