package net.javaguides.springboot.springsecurity.service;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springsecurity.model.PackageDto;
import net.javaguides.springboot.springsecurity.model.PathFileResponse;
import net.javaguides.springboot.springsecurity.model.dto.ChangePackageRequest;
import net.javaguides.springboot.springsecurity.model.entity.HairCut;
import net.javaguides.springboot.springsecurity.model.entity.InfoFile;
import net.javaguides.springboot.springsecurity.model.entity.StatisticsFile;
import net.javaguides.springboot.springsecurity.model.entity.User;
import net.javaguides.springboot.springsecurity.repository.HairCutRepository;
import net.javaguides.springboot.springsecurity.util.DateUtil;
import net.javaguides.springboot.springsecurity.util.FormatFileUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PathFileServiceImpl implements PathFileService {

    private final UserUploadService userUploadService;
    private final HairCutRepository hairCutRepository;
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
        HairCut hairCut = HairCut.builder()
                .infoFile(infoFile)
                .path(path)
                .statisticsFile(StatisticsFile.builder()
                        .countDownload(0)
                        .build())
                .privacy(false)
                .name(fileName)
                .build();
        User user = userUploadService.getByEmail(email);
        user.getHairCuts().add(hairCut);
        userUploadService.save(user);
    }

    @Override
    public List<HairCut> getHairCutList() {
        return hairCutRepository.findAll();
    }

    @Override
    public void changePrivacy(Long id, String username) {
        User user = userUploadService.getByEmail(username);
        HairCut hairCut = hairCutRepository.getOne(id);
        if (hairCut.getInfoFile().getUsername().equals(user.getEmail())) {
            hairCut.setPrivacy(!hairCut.getPrivacy());
            hairCutRepository.save(hairCut);
        } else {
            errorUserService.save(USER_NOT_CONFIRM_ERROR_MESSAGE, username);
        }
    }

    @Override
    public void deleteHairCutService(Long id) {
        HairCut hairCut = hairCutRepository.getOne(id);
        hairCutRepository.delete(hairCut);
    }

    @Override
    public List<HairCut> findByNameAndUsername(String name, String username) {
        return hairCutRepository.findPathFileByNameContainingOrPathContainingOrInfoFileTypeContainingAndInfoFileUsername(name, name, name, username);
    }

    @Override
    public PathFileResponse getData(String name) {
        HairCut hairCut = hairCutRepository.findByNameContaining(name.toLowerCase());
        return PathFileResponse.builder()
                .text(getText(hairCut.getPath()))
                .build();
    }

    @Override
    public PackageDto getPath(String name) {
        HairCut hairCut = hairCutRepository.findByNameContaining(name.toLowerCase());
        return PackageDto.builder()
                .path(hairCut.getPath())
                .build();
    }

    @Override
    public void deletePackage(String name) {
        HairCut hairCut = hairCutRepository.findByNameContaining(name.toLowerCase());
        hairCut.setPath("");
        hairCutRepository.save(hairCut);
    }

    @Override
    public void changePackage(ChangePackageRequest changePackageRequest) {
        HairCut hairCut = hairCutRepository.findByPathContaining(changePackageRequest.getOldPath());
        hairCut.setPath(changePackageRequest.getNewPath());
        hairCutRepository.save(hairCut);
    }

    @Override
    public void save(HairCut hairCut) {
        hairCutRepository.save(hairCut);
    }

    @SneakyThrows
    private String getText(String path) {
        String text = "";
        File f = new File(path);
        BufferedReader fin = new BufferedReader(new FileReader(f));
        String line;
        while ((line = fin.readLine()) != null) text += text + line;
        return text;
    }
}
