package examprep.softuni.web.model;

import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterModel {
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters")
    private String username;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Please enter a valid email")
    private String email;
    @NotNull(message = "Budget cannot be null")
    @PositiveOrZero(message = "Budget must be zero or a positive number")
    private BigDecimal budget;
    @NotNull(message = "Email cannot be null")
    @Size(min = 8, max = 255, message = "Please enter a valid password")
    private String password;
    @NotNull(message = "Please confirm password")
    @Size(min = 8, max = 255, message = "Please enter a valid password")
    private String confirmPassword;
}
