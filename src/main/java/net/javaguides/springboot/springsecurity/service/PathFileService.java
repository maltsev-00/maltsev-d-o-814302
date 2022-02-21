package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.PackageDto;
import net.javaguides.springboot.springsecurity.model.PathFileResponse;
import net.javaguides.springboot.springsecurity.model.dto.ChangePackageRequest;
import net.javaguides.springboot.springsecurity.model.entity.PathFile;

import java.util.List;

public interface PathFileService {

    void save(String path,String email,String fileName);

    List<PathFile> getPathList(String email);

    void changePrivacy(Long id,String username);

    void deleteFile(Long id,String name);

    List<PathFile> findByNameAndUsername(String name, String username);

    PathFileResponse getData(String name);

    PackageDto getPath(String name);

    void deletePackage(String name);

    void changePackage(ChangePackageRequest changePackageRequest);
}
