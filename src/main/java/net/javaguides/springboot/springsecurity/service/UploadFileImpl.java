package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class UploadFileImpl implements UploadFile {

    private final FileService fileService;
    private final ErrorUserService errorUserService;

    @Override
    public File getFile(Long id) {
        var file = new File(fileService.getPath(id));
        if (!file.exists()) {
            errorUserService.save("File is not found", "null");
            throw new RuntimeException();
        }
        return file;
    }

    @Override
    public InputStreamResource getInputStreamResource(File file) {
        try {
            return new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            errorUserService.save(ex.getMessage(), "null");
            throw new RuntimeException();
        }
    }
}
