package examprep.softuni.service.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemViewModel {
    private String id;
    private String name;
    private String imgUrl;
    private String description;
    private BigDecimal price;
}
