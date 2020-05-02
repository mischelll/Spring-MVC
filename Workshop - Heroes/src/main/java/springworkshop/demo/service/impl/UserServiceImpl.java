package springworkshop.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springworkshop.demo.data.models.User;
import springworkshop.demo.data.repositories.RoleRepository;
import springworkshop.demo.data.repositories.UserRepository;
import springworkshop.demo.service.AuthServiceValidation;
import springworkshop.demo.service.RoleService;
import springworkshop.demo.service.UserService;
import springworkshop.demo.service.models.UserHeroServiceModel;
import springworkshop.demo.service.models.UserServiceModel;

import java.util.LinkedHashSet;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final AuthServiceValidation authServiceValidation;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository, RoleService roleService, AuthServiceValidation authServiceValidation, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.authServiceValidation = authServiceValidation;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public boolean register(UserServiceModel user) {
        if (!authServiceValidation.isValid(user)){
            return false;
        }


        User userReg = this.modelMapper.map(user, User.class);
        userReg.setPassword(passwordEncoder.encode(userReg.getPassword()));


        this.roleService.seedRolesInDb();
        if (this.userRepository.count() == 0) {
            userReg.setAuthorities(this.roleService.findAll());
        } else {
            userReg.setAuthorities(new LinkedHashSet<>());

            userReg.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }
        this.userRepository.saveAndFlush(userReg);
        return true;
    }

    @Override
    public UserHeroServiceModel findByUsername(String username) {
        User byUsername = this.userRepository.findByUsername(username);
        UserHeroServiceModel map = this.modelMapper.map(byUsername, UserHeroServiceModel.class);
        map.setHero(byUsername.getHero());
        return map;
    }
}
