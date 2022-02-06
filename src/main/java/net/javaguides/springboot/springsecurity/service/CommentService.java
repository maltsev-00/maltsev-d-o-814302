package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.dto.CommentDto;
import net.javaguides.springboot.springsecurity.model.response.CommentResponse;

import java.util.List;

public interface CommentService {
    void save(CommentDto comment,Long id,String email);

    List<CommentResponse> findById(Long id);
}
