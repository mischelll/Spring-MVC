package com.softuni.springworkshop.service;

import com.softuni.springworkshop.dao.entities.Role;

import java.util.Set;

public interface RoleService {
    void seedRolesInDb();
    Set<Role> findAll();
    Role findByAuthority(String auth);
}
