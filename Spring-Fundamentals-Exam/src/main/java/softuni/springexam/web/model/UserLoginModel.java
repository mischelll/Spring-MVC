package softuni.springexam.web.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginModel {
    @NotNull
    @Size(min = 3,max = 20,message = "Username must be between 3 and 20 characters")
    private String username;
    @NotNull
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 characters")
    private String password;
}
