package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
