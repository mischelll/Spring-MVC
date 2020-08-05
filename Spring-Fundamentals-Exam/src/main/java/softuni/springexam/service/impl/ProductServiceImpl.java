package softuni.springexam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.springexam.dao.entity.Category;
import softuni.springexam.dao.entity.CategoryName;
import softuni.springexam.dao.entity.Product;
import softuni.springexam.dao.repository.ProductRepository;
import softuni.springexam.service.CategoryService;
import softuni.springexam.service.ProductService;
import softuni.springexam.service.model.ProductAddServiceModel;
import softuni.springexam.web.model.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public ProductServiceImpl(ModelMapper mapper, ProductRepository productRepository, CategoryService categoryService) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public ProductAddServiceModel add(ProductAddServiceModel product) {
        Product map = this.mapper.map(product, Product.class);
        if (this.productRepository.findByName(product.getName()) != null) {
            return null;
        }
        Category category = this.categoryService.findByName(product.getCategory().name());
        map.setCategory(category);

        return this.mapper
                .map(this.productRepository
                                .saveAndFlush(map),
                        ProductAddServiceModel.class);
    }

    @Override
    public List<ProductViewModel> findProductsByCategory(String category) {
        Category byName = this.categoryService.findByName(category);
        List<Product> byCategory = this.productRepository.findByCategory(byName);

        return byCategory
                .stream()
                .map(product -> this.mapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return this.productRepository
                .findAll()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void buyProduct(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void buyAllProducts() {
        this.productRepository.deleteAll();
    }
}
