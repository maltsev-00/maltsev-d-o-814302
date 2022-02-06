package net.javaguides.springboot.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springsecurity.model.dto.CommentDto;
import net.javaguides.springboot.springsecurity.model.response.CommentResponse;
import net.javaguides.springboot.springsecurity.service.CommentService;
import net.javaguides.springboot.springsecurity.service.UserLogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("files/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final UserLogService userLogService;
    @Value("${email}")
    private String email;

    @PostMapping("{id}")
    public void saveComment(@RequestBody CommentDto comment, @PathVariable("id") Long id) {
        log.info("Save comment with message:{}", comment.getMessage());
        userLogService.saveLog(comment.getMessage(), email);
        commentService.save(comment, id, email);
    }

    @GetMapping("/{id}")
    public List<CommentResponse> getComments(@PathVariable("id") Long id, Model model) {
        log.info("Get comments with id: {}", id.toString());
        userLogService.saveLog(id.toString(), email);
        return commentService.findById(id);
    }
}
