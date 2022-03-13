package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.model.entity.HairCut;
import net.javaguides.springboot.springsecurity.repository.HairCutRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final HairCutRepository hairCutRepository;

    @Override
    public String getPath(Long id) {
        HairCut hairCut = hairCutRepository.getOne(id);
        hairCut.getStatisticsFile().setCountDownload(hairCut.getStatisticsFile().getCountDownload() + 1);
        hairCutRepository.save(hairCut);
        return hairCutRepository.getOne(id).getPath();
    }
}
