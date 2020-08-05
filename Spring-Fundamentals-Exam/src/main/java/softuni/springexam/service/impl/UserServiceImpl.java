package softuni.springexam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.springexam.dao.entity.User;
import softuni.springexam.dao.repository.UserRepository;
import softuni.springexam.service.UserService;
import softuni.springexam.service.model.UserLoginServiceModel;
import softuni.springexam.service.model.UserRegisterServiceModel;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserRegisterServiceModel register(UserRegisterServiceModel user) {
        User regUser = this.mapper.map(user, User.class);
        if (this.userRepository.findByUsername(user.getUsername()) != null){
            return null;
        }

        return this.mapper
                .map(this.userRepository
                                .saveAndFlush(regUser),
                        UserRegisterServiceModel.class);
    }

    @Override
    public UserLoginServiceModel login(UserLoginServiceModel user) {
        User byUsername = this.userRepository.findByUsername(user.getUsername());
        if (byUsername == null || !byUsername.getPassword().equals(user.getPassword())) {
            return null;
        }
        return this
                .mapper
                .map(this.userRepository
                                .findByUsername(user.getUsername()),
                        UserLoginServiceModel.class);

    }
}

