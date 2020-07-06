package softuni.springexam.web.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterModel {
    @NotNull
    @Size(min = 3,max = 20,message = "Username must be between 3 and 20 characters")
    private String username;
    @NotEmpty(message ="Email cannot be empty" )
    @Email(message = "Please enter a valid email")
    private String email;
    @NotNull
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 characters")
    private String password;
    @NotNull
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 characters")
    private String confirmPassword;
}
