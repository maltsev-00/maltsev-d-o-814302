package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.PathFileRequestData;
import net.javaguides.springboot.springsecurity.model.PathFileResponse;
import net.javaguides.springboot.springsecurity.model.entity.HairCut;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void save(HairCut hairCut);

    PathFileResponse getDataFile(PathFileRequestData pathFileRequestData);
}
