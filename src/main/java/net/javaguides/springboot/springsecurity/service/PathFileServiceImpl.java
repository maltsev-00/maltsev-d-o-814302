package net.javaguides.springboot.springsecurity.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springsecurity.model.entity.HairCut;
import net.javaguides.springboot.springsecurity.repository.HairCutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PathFileServiceImpl implements PathFileService {

    private final HairCutRepository hairCutRepository;

    @Override
    public List<HairCut> getHairCutList() {
        return hairCutRepository.findAll();
    }


    @Override
    public void deleteHairCutService(Long id) {
        HairCut hairCut = hairCutRepository.getOne(id);
        hairCutRepository.delete(hairCut);
    }

    @Override
    public void save(HairCut hairCut) {
        hairCutRepository.save(hairCut);
    }

}
