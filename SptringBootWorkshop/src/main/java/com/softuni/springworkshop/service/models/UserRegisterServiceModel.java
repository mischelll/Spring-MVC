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
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 32, message = "Please enter a valid username")
    private String username;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 255, message = "Please enter a valid password")
    private String password;
    @NotEmpty
    @Size(min = 8, max = 255, message = "Please enter a valid password")
    private String confirmPassword;
    @Pattern(regexp ="https:\\/\\/github\\.com\\/com\\/.+\\/.+",message = "Enter git address following this pattern")
    private String git;
}
