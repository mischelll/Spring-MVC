package examprep.softuni.web.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginModel {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
