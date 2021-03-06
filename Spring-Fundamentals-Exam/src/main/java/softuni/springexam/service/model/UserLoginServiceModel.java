package softuni.springexam.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginServiceModel {
    @NotNull
    @Size(min = 3,max = 20,message = "Username must be between 3 and 20 characters")
    private String username;
    @NotNull
    @Size(min = 3,max = 20,message = "Password must be between 3 and 20 characters")
    private String password;
}
