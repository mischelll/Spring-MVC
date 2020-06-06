package com.softuni.springworkshop.dao.repositories;

import com.softuni.springworkshop.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
