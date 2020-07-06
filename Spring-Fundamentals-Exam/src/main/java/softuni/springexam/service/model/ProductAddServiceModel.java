package softuni.springexam.service.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.springexam.dao.entity.CategoryName;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductAddServiceModel {
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 20, message = "Product name must be between 3 and 20 characters")
    private String name;
    @Size(min = 5, message = "Description must be at least 5 characters")
    private String description;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    private LocalDateTime neededBefore;
    @NonNull
    @Positive(message = "Price must be a positive number")
    private BigDecimal price;
    @NonNull
    private CategoryName category;
}
