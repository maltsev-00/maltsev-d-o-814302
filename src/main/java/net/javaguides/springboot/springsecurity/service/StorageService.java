package net.javaguides.springboot.springsecurity.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void store(MultipartFile file,String username);
}
