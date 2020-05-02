package springworkshop.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springworkshop.demo.data.models.Role;
import springworkshop.demo.data.repositories.RoleRepository;
import springworkshop.demo.service.RoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRolesInDb() {
        if (this.roleRepository.count() == 0){
            this.roleRepository.saveAndFlush(new Role("ROLE_USER"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ROOT"));
        }
    }

    @Override
    public Set<Role> findAll() {
        return new HashSet<>(this.roleRepository.findAll());
    }

    @Override
    public Role findByAuthority(String authority) {
        return this.roleRepository.findByAndAuthority(authority);
    }
}
