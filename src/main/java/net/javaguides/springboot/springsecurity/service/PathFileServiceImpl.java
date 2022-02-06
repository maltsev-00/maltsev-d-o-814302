package net.javaguides.springboot.springsecurity.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springsecurity.model.entity.InfoFile;
import net.javaguides.springboot.springsecurity.model.entity.PathFile;
import net.javaguides.springboot.springsecurity.model.entity.StatisticsFile;
import net.javaguides.springboot.springsecurity.model.entity.User;
import net.javaguides.springboot.springsecurity.repository.PathFileRepository;
import net.javaguides.springboot.springsecurity.util.DateUtil;
import net.javaguides.springboot.springsecurity.util.FormatFileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PathFileServiceImpl implements PathFileService {

    private final UserUploadService userUploadService;
    private final PathFileRepository pathFileRepository;
    private final DateUtil dateUtil;
    private final FormatFileUtil formatFileUtil;
    private final ErrorUserService errorUserService;

    private static final String FILE_IS_NOT_FOUND_MESSAGE = "Failed to delete the file";
    private static final String USER_NOT_CONFIRM_ERROR_MESSAGE = "User not confirm";

    @Override
    public void save(String path, String email, String fileName) {
        InfoFile infoFile = InfoFile.builder()
                .type(formatFileUtil.getFormat(fileName))
                .username(email)
                .time(dateUtil.getLocalDate())
                .build();
        PathFile pathFile = PathFile.builder()
                .infoFile(infoFile)
                .path(path)
                .statisticsFile(StatisticsFile.builder()
                        .countDownload(0)
                        .build())
                .privacy(false)
                .name(fileName)
                .build();
        User user = userUploadService.getByEmail(email);
        user.getPathFiles().add(pathFile);
        userUploadService.save(user);
    }

    @Override
    public List<PathFile> getPathList(String email) {
        User user = userUploadService.getByEmail(email);
        List<PathFile> pathFileList = pathFileRepository.findPathFileByPrivacy(true);
        Set<PathFile> pathFiles = user.getPathFiles();
        pathFiles.addAll(pathFileList);
        return List.copyOf(pathFiles);
    }

    @Override
    public void changePrivacy(Long id, String username) {
        User user = userUploadService.getByEmail(username);
        PathFile pathFile = pathFileRepository.getOne(id);
        if (pathFile.getInfoFile().getUsername().equals(user.getEmail())) {
            pathFile.setPrivacy(!pathFile.getPrivacy());
            pathFileRepository.save(pathFile);
        } else {
            errorUserService.save(USER_NOT_CONFIRM_ERROR_MESSAGE, username);
        }
    }

    @Override
    public void deleteFile(Long id, String name) {
        PathFile pathFile = pathFileRepository.getOne(id);
        String username = pathFile.getInfoFile().getUsername();
        if (name.equals(username)) {
            File file = new File(pathFile.getPath());
            if (file.delete()) {
                log.info("File deleted successfully");
            } else {
                errorUserService.save(FILE_IS_NOT_FOUND_MESSAGE, username);
            }
            pathFileRepository.delete(pathFile);
        } else {
            errorUserService.save(USER_NOT_CONFIRM_ERROR_MESSAGE, username);
        }
    }

    @Override
    public List<PathFile> findByNameAndUsername(String name, String username) {
        return pathFileRepository.findPathFileByNameContainingOrPathContainingOrInfoFileTypeContainingAndInfoFileUsername(name, name, name, username);
    }

}
