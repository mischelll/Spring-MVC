package springworkshop.demo.service;

import springworkshop.demo.data.models.Role;

import java.util.Set;

public interface RoleService {
    void seedRolesInDb();
    Set<Role> findAll();
    Role findByAuthority(String auth);
}
