package com.softuni.springworkshop.dao.repositories;


import com.softuni.springworkshop.dao.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,String> {
}
