package com.softuni.springworkshop.service;

import com.softuni.springworkshop.service.models.UserRegisterServiceModel;
import com.softuni.springworkshop.web.models.UserRegisterModel;

public interface UserAuthService {
    boolean isValid(UserRegisterServiceModel user);
}
