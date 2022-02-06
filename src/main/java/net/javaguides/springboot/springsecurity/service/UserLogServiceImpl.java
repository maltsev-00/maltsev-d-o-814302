package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.model.entity.UserLog;
import net.javaguides.springboot.springsecurity.repository.UserLogRepository;
import net.javaguides.springboot.springsecurity.util.DateUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLogServiceImpl implements UserLogService {

    private final UserLogRepository userLogRepository;
    private final DateUtil dateUtil;

    @Override
    public void saveLog(String body, String email) {
        UserLog userLog = UserLog.builder()
                .body(body)
                .email(email)
                .time(dateUtil.getLocalDate())
                .build();
        userLogRepository.save(userLog);
    }
}
