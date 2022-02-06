package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.entity.ErrorUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorUserRepository extends JpaRepository<ErrorUserEntity,Long> {
}
