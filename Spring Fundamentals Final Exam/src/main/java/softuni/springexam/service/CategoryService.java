package softuni.springexam.service;

import softuni.springexam.dao.entity.Category;

public interface CategoryService {
    void seedCategories();

    Category findByName(String name);
}
