package examprep.softuni.service.model.bind;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterServiceModel {
    @NonNull
    @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters")
    private String username;
    @NonNull
    @Email(message = "Please enter a valid email")
    private String email;
    @NonNull
    @PositiveOrZero(message = "Budget must be zero or a positive number")
    private BigDecimal budget;
    @NonNull
    @Size(min = 8, max = 255, message = "Please enter a valid password")
    private String password;
    @NonNull
    @Size(min = 8, max = 255, message = "Please enter a valid password")
    private String confirmPassword;
}
