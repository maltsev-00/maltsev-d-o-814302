package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.PackageDto;
import net.javaguides.springboot.springsecurity.model.PathFileResponse;
import net.javaguides.springboot.springsecurity.model.dto.ChangePackageRequest;
import net.javaguides.springboot.springsecurity.model.entity.HairCut;

import java.util.List;

public interface PathFileService {

    void save(String path,String email,String fileName);

    List<HairCut> getHairCutList();

    void changePrivacy(Long id,String username);

    void deleteHairCutService(Long id);

    List<HairCut> findByNameAndUsername(String name, String username);

    PathFileResponse getData(String name);

    PackageDto getPath(String name);

    void deletePackage(String name);

    void changePackage(ChangePackageRequest changePackageRequest);

    void save(HairCut hairCut);
}
