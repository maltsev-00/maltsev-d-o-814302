package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.model.entity.PathFile;
import net.javaguides.springboot.springsecurity.repository.PathFileRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final PathFileRepository pathFileRepository;

    @Override
    public String getPath(Long id) {
        PathFile pathFile = pathFileRepository.getOne(id);
        pathFile.getStatisticsFile().setCountDownload(pathFile.getStatisticsFile().getCountDownload() + 1);
        pathFileRepository.save(pathFile);
        return pathFileRepository.getOne(id).getPath();
    }
}
