package com.softuni.springworkshop.service.impl;

import com.softuni.springworkshop.dao.entities.Role;
import com.softuni.springworkshop.dao.entities.User;
import com.softuni.springworkshop.dao.repositories.UserRepository;
import com.softuni.springworkshop.service.RoleService;
import com.softuni.springworkshop.service.UserAuthService;
import com.softuni.springworkshop.service.UserService;
import com.softuni.springworkshop.service.models.UserLoginServiceModel;
import com.softuni.springworkshop.service.models.UserRegisterServiceModel;
import com.softuni.springworkshop.web.models.RoleAddModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserAuthService userAuthService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, PasswordEncoder passwordEncoder, RoleService roleService, UserAuthService userAuthService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userAuthService = userAuthService;
    }

    @Override
    public UserRegisterServiceModel register(UserRegisterServiceModel user) {
//        if (!userAuthService.isValid(user)){
//            return false;
//        }
        User regUser = this.mapper.map(user, User.class);
        regUser.setPassword(this.passwordEncoder.encode(regUser.getPassword()));
        if (this.userRepository.count() == 0) {
            this.roleService.seedRolesInDb();
            regUser.setAuthorities(this.roleService.findAll());
        } else {
            regUser.setAuthorities(new LinkedHashSet<>());
            regUser.getAuthorities().add(this.roleService.findByAuthority("USER"));
        }

        this.userRepository.saveAndFlush(regUser);

        return this.mapper.map(regUser, UserRegisterServiceModel.class);
    }

    @Override
    public boolean login(UserLoginServiceModel user) {
        User byUsername = this.userRepository.findByUsername(user.getUsername());
        boolean passMatch = this.passwordEncoder.matches(user.getPassword(), byUsername.getPassword());
        return byUsername != null && passMatch;
    }

    @Override
    public void changeRole(RoleAddModel roleAdd) {
        User byUsername = this.userRepository.findByUsername(roleAdd.getUsername());
        if (byUsername == null){
            return;
        }
        Set<Role> authorities = byUsername.getAuthorities();
        authorities.add(this.roleService.findByAuthority(roleAdd.getRole().toUpperCase()));

        byUsername.setAuthorities(authorities);

        this.userRepository.saveAndFlush(byUsername);
    }

    @Override
    public List<String> getUsernames() {

        return this.userRepository
                .findAll()
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s);
    }
}
