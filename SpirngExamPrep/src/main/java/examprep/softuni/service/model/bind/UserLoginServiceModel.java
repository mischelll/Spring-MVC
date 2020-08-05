package examprep.softuni.service.model.bind;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginServiceModel {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
