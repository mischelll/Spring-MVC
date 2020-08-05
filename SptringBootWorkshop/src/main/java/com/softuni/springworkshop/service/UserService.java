package com.softuni.springworkshop.service;

import com.softuni.springworkshop.service.models.UserLoginServiceModel;
import com.softuni.springworkshop.service.models.UserRegisterServiceModel;
import com.softuni.springworkshop.web.models.RoleAddModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserRegisterServiceModel register(UserRegisterServiceModel user);
    boolean login(UserLoginServiceModel user);
    void changeRole(RoleAddModel roleAdd);
    List<String> getUsernames();
}
