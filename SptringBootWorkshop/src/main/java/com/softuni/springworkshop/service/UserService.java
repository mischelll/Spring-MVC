package com.softuni.springworkshop.service;

import com.softuni.springworkshop.service.models.UserLoginServiceModel;
import com.softuni.springworkshop.service.models.UserRegisterServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean register(UserRegisterServiceModel user);
    boolean login(UserLoginServiceModel user);
}
