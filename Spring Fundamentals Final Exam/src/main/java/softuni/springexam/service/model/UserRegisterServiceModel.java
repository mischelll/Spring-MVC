package softuni.springexam.service.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterServiceModel {
    @NotNull
    @Size(min = 3,max = 20,message = "Username must be between 3 and 20 characters")
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 characters")
    private String password;
    @NotNull
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 characters")
    private String confirmPassword;
}
