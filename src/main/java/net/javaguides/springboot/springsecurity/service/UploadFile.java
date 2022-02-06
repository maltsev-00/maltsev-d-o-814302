package net.javaguides.springboot.springsecurity.service;

import org.springframework.core.io.InputStreamResource;

import java.io.File;

public interface UploadFile {
    File getFile(Long id);

    InputStreamResource getInputStreamResource(File file);
}
