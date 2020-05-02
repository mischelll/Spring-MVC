package springworkshop.demo.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springworkshop.demo.data.models.Hero;
@Getter
@Setter
@NoArgsConstructor
public class UserHeroServiceModel {
    private String username;
    private String email;
    private Hero hero;
}
