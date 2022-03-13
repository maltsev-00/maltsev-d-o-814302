package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.model.PathFileRequestData;
import net.javaguides.springboot.springsecurity.model.PathFileResponse;
import net.javaguides.springboot.springsecurity.model.entity.HairCut;
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
    public void save(HairCut hairCut, ) {
    }

    @Override
    public PathFileResponse getDataFile(PathFileRequestData pathFileRequestData) {
        return pathFileService.getData(pathFileRequestData.getName());
    }
}
