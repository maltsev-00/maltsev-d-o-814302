package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.entity.HairCut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HairCutRepository extends JpaRepository<HairCut, Long> {
    List<HairCut> findPathFileByPrivacy(boolean privacy);

    List<HairCut> findPathFileByNameContainingOrPathContainingOrInfoFileTypeContainingAndInfoFileUsername
            (String name,
             String path,
             String type,
             String username);
    HairCut findByPathContaining(String path);

    HairCut findByNameContaining(String name);
}
