package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.PathFileRequestData;
import net.javaguides.springboot.springsecurity.model.PathFileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void store(MultipartFile file,String username);

    PathFileResponse getDataFile(PathFileRequestData pathFileRequestData);
}
