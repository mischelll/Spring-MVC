package com.softuni.springworkshop.web.models;

import lombok.*;

import javax.validation.constraints.*;


public class UserRegisterModel {
    @NonNull
    @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters")
    private String username;
    @NonNull
    @Email(message = "Please enter a valid email")
    private String email;
    @NonNull
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password;
    @NonNull
    @Size(min = 8, max = 255, message = "Please enter a valid password")
    private String confirmPassword;
    @NonNull
    @Pattern(regexp ="https://github.com/.+/.+",message = "Enter git address following this pattern")
    private String git;

    public UserRegisterModel() {
    }

    public UserRegisterModel(@NotEmpty(message = "Username cannot be empty") @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters") String username, @NotEmpty(message = "Email cannot be empty") @Email(message = "Please enter a valid email") String email, @NotEmpty(message = "Password cannot be empty") @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters") String password, @NotEmpty @Size(min = 8, max = 255, message = "Please enter a valid password") String confirmPassword, @Pattern(regexp = "https:\\/\\/github\\.com\\/com\\/.+\\/.+", message = "Enter git address following this pattern") String git) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.git = git;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
