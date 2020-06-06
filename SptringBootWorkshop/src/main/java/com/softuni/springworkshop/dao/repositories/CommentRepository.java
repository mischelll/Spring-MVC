package com.softuni.springworkshop.dao.repositories;

import com.softuni.springworkshop.dao.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {
}
