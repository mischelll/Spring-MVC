package examprep.softuni.service;

import examprep.softuni.service.model.bind.UserLoginServiceModel;
import examprep.softuni.service.model.bind.UserRegisterServiceModel;

public interface UserService {
    UserRegisterServiceModel register(UserRegisterServiceModel user);

    UserLoginServiceModel login(UserLoginServiceModel user);
}
