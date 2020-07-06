package softuni.springexam.service;

import softuni.springexam.service.model.ProductAddServiceModel;
import softuni.springexam.web.model.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductAddServiceModel add(ProductAddServiceModel product);
    List<ProductViewModel> findProductsByCategory(String category);
    BigDecimal calculateTotalPrice();
    void buyProduct(String id);
    void buyAllProducts();
}
