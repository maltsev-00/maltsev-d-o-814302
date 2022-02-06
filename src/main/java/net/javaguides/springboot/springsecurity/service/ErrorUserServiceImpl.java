package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springsecurity.model.entity.ErrorUserEntity;
import net.javaguides.springboot.springsecurity.repository.ErrorUserRepository;
import net.javaguides.springboot.springsecurity.util.DateUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ErrorUserServiceImpl implements ErrorUserService {

    private final ErrorUserRepository errorUserRepository;
    private final DateUtil dateUtil;

    @Override
    public void save(String message, String email) {
        log.error(message);
        errorUserRepository.save(ErrorUserEntity.builder()
                .date(dateUtil.getLocalDate())
                .email(email)
                .build());
    }
}
