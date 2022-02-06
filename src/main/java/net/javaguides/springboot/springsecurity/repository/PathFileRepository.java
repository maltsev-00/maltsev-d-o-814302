package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.entity.PathFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PathFileRepository extends JpaRepository<PathFile, Long> {
    List<PathFile> findPathFileByPrivacy(boolean privacy);

    List<PathFile> findPathFileByNameContainingOrPathContainingOrInfoFileTypeContainingAndInfoFileUsername
            (String name,
             String path,
             String type,
             String username);
}
