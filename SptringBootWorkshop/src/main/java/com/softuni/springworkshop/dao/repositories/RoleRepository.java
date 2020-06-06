package com.softuni.springworkshop.dao.repositories;


import com.softuni.springworkshop.dao.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByAndAuthority(String authority);
}
