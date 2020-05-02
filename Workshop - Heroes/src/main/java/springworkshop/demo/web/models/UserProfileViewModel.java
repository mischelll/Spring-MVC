package springworkshop.demo.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springworkshop.demo.data.models.Hero;


 @Getter
 @Setter
 @NoArgsConstructor
public class UserProfileViewModel {
    private String username;
    private String email;
    private Hero hero;
}
