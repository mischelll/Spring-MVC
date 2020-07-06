package softuni.springexam.service.impl;


import org.springframework.stereotype.Service;
import softuni.springexam.dao.entity.Category;
import softuni.springexam.dao.entity.CategoryName;
import softuni.springexam.dao.repository.CategoryRepository;
import softuni.springexam.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays
                    .stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository.saveAndFlush(new Category(categoryName,
                                String.format("Some description for %s.", categoryName.name())));
                    });
        }
    }

    @Override
    public Category findByName(String name) {
        return this.categoryRepository.findByName(CategoryName.valueOf(name));
    }
}
