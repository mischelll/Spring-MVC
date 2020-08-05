package examprep.softuni.web.model;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemAddModel {
    @NonNull
    @Size(min = 2, max = 50, message = "Name must be between 2-50 characters long")
    private String name;
    @NonNull
    @Size(max = 150, message = "Description must not be more than 150 characters long")
    private String description;
    @NotNull(message = "Please select category!")
    private String category;
    @NotNull(message = "Please select gender!")
    private String gender;
    @DecimalMin(value = "1.00", message = "Price must be at least 1.00$")
    @Digits(integer = 4, fraction = 2,
            message = "Price must have max 4 integral digits and max 2 fractional digits")
    private BigDecimal price;

}
