package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.entity.HairCut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HairCutRepository extends JpaRepository<HairCut, Long> {
}
