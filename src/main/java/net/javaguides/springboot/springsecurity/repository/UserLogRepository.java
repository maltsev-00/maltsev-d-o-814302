package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}
