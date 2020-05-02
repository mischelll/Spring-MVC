package springworkshop.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springworkshop.demo.data.repositories.UserRepository;
import springworkshop.demo.service.AuthServiceValidation;
import springworkshop.demo.service.models.UserServiceModel;

@Service
public class AuthServiceValidationImpl implements AuthServiceValidation {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceValidationImpl( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(UserServiceModel user) {
        return isEmailValid(user.getEmail())
                && isUsernameUnique(user.getUsername())
                && arePasswordMatching(user.getPassword(), user.getConfirmPassword())
                && isPasswordValid(user.getPassword());
    }

    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean arePasswordMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isUsernameUnique(String username) {
        return this.userRepository.findByUsername(username)== null;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }
}
