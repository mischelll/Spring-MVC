package springworkshop.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springworkshop.demo.service.models.UserHeroServiceModel;
import springworkshop.demo.service.models.UserServiceModel;

public interface UserService extends UserDetailsService {
    boolean register(UserServiceModel user);
    UserHeroServiceModel findByUsername(String username);
}
