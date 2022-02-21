package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.model.PathFileRequestData;
import net.javaguides.springboot.springsecurity.model.PathFileResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final PathFileService pathFileService;
    private final ErrorUserService errorUserService;

    private static final String FILE_IS_EMPTY_ERROR_MESSAGE = "File is empty";

    @Override
    public void store(MultipartFile file, String username) {
        final String pathPackage = username + "/";
        if (file.isEmpty()) {
            errorUserService.save(FILE_IS_EMPTY_ERROR_MESSAGE, username);
        }
        File dir = new File(pathPackage);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            final var fullPath = pathPackage + fileName;
            pathFileService.save(fullPath, username, fileName);
            Path path = Paths.get(fullPath);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            errorUserService.save(e.getMessage(), username);
        }
    }

    @Override
    public PathFileResponse getDataFile(PathFileRequestData pathFileRequestData) {
        return pathFileService.getData(pathFileRequestData.getName());
    }
}
