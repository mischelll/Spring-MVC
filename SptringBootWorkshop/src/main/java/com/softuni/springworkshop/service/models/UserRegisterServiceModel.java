package com.softuni.springworkshop.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterServiceModel {
    @NotEmpty
    @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters")
    private String username;
    @NotEmpty
    @Email(message = "Please enter a valid email")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password;
    @NotEmpty
    @Size(min = 8, max = 255, message = "Please enter a valid password")
    private String confirmPassword;
    @Pattern(regexp ="https:\\/\\/github\\.com\\/com\\/.+\\/.+",message = "Enter git address following this pattern")
    private String git;
}
