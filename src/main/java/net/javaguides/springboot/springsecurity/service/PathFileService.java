package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.PackageDto;
import net.javaguides.springboot.springsecurity.model.PathFileResponse;
import net.javaguides.springboot.springsecurity.model.dto.ChangePackageRequest;
import net.javaguides.springboot.springsecurity.model.entity.HairCut;

import java.util.List;

public interface PathFileService {

    List<HairCut> getHairCutList();


    void deleteHairCutService(Long id);


    void save(HairCut hairCut);
}
