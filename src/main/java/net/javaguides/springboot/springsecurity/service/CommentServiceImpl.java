package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.converter.CommentConverter;
import net.javaguides.springboot.springsecurity.model.dto.CommentDto;
import net.javaguides.springboot.springsecurity.model.entity.Comment;
import net.javaguides.springboot.springsecurity.model.entity.PathFile;
import net.javaguides.springboot.springsecurity.model.response.CommentResponse;
import net.javaguides.springboot.springsecurity.repository.PathFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PathFileRepository fileRepository;
    private final CommentConverter commentConverter;

    @Override
    public void save(CommentDto comment, Long id, String email) {
        PathFile pathFile = fileRepository.getOne(id);
        Comment commentEntity = Comment.builder()
                .email(email)
                .message(comment.getMessage())
                .build();
        pathFile.getComments().add(commentEntity);
        fileRepository.save(pathFile);
    }

    @Override
    public List<CommentResponse> findById(Long id) {
        Set<Comment> comments = fileRepository.getOne(id).getComments();
        return comments.stream()
                .map(commentConverter::convertToToResponse)
                .collect(Collectors.toList());
    }
}
