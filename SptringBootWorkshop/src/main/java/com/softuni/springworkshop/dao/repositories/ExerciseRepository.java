package com.softuni.springworkshop.dao.repositories;

import com.softuni.springworkshop.dao.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,String> {
    Exercise findByName(String name);
}
