package springworkshop.demo.service;

import springworkshop.demo.service.models.UserServiceModel;

public interface AuthServiceValidation {
    boolean isValid(UserServiceModel user);
}
