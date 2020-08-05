package com.softuni.springworkshop.service;

import com.softuni.springworkshop.dao.entities.Role;
import com.softuni.springworkshop.web.models.RoleAddModel;

import java.util.Set;

public interface RoleService {
    void seedRolesInDb();
    Set<Role> findAll();
    Role findByAuthority(String auth);

}
