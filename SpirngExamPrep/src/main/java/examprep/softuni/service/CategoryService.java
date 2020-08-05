package examprep.softuni.service;

import examprep.softuni.dao.entity.Category;

public interface CategoryService {
    void initCategories();

    Category findByName(String name);
}
