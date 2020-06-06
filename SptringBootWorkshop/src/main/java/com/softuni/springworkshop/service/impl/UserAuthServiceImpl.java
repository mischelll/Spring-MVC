package com.softuni.springworkshop.service.impl;

import com.softuni.springworkshop.dao.repositories.UserRepository;
import com.softuni.springworkshop.service.UserAuthService;
import com.softuni.springworkshop.service.models.UserRegisterServiceModel;
import com.softuni.springworkshop.web.models.UserRegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String EMAIL_REGEX = "^[A-Za-z_]+@[a-z]+\\.[a-z]{2,4}$";
    private static final String PASSWORD_REGEX = "^(?<pass>[A-Z]{1,}[a-z]{1,}[0-9]+)$";

    @Autowired
    public UserAuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(UserRegisterServiceModel user) {
        return isEmailValid(user.getEmail())
                && isUsernameValid(user.getUsername())
                && isPasswordValid(user.getPassword())
                && arePasswordsMatching(user.getPassword(), user.getConfirmPassword());
    }

    private boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        boolean match = matcher.matches();
        return match;
    }

    private boolean isUsernameValid(String username) {
        boolean minChars  = username.length() >= 3 && username.length() <= 32;
        boolean unique = this.userRepository.findByUsername(username) == null;

        return minChars && unique;

    }

    private boolean isPasswordValid(String password) {
        boolean minChars = password.length() >= 8 && password.length() <= 255;
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        boolean match = matcher.matches();
        return minChars && match;
    }

    private boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
