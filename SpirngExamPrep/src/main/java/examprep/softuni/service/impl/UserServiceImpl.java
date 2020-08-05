package examprep.softuni.service.impl;

import examprep.softuni.dao.entity.User;
import examprep.softuni.dao.repository.UserRepository;
import examprep.softuni.service.UserService;
import examprep.softuni.service.model.bind.UserLoginServiceModel;
import examprep.softuni.service.model.bind.UserRegisterServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

        return this.mapper
                .map(this.userRepository
                                .saveAndFlush(regUser),
                        UserRegisterServiceModel.class);

    }

    @Override
    public UserLoginServiceModel login(UserLoginServiceModel user) {
        User byUsername = this.userRepository.findByUsername(user.getUsername());
        if (byUsername == null || !byUsername.getPassword().equals(user.getPassword())){
            return null;
        }
        return this
                .mapper
                .map(this.userRepository
                .findByUsername(user.getUsername()),
                        UserLoginServiceModel.class );

    }
}
