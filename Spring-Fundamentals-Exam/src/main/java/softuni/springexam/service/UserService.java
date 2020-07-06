package softuni.springexam.service;

import softuni.springexam.service.model.UserLoginServiceModel;
import softuni.springexam.service.model.UserRegisterServiceModel;

public interface UserService {
    UserRegisterServiceModel register(UserRegisterServiceModel user);

    UserLoginServiceModel login(UserLoginServiceModel user);
}
