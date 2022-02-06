package net.javaguides.springboot.springsecurity.service;

import junit.framework.TestCase;
import net.javaguides.springboot.springsecurity.converter.CommentConverter;
import net.javaguides.springboot.springsecurity.model.entity.Comment;
import net.javaguides.springboot.springsecurity.model.response.CommentResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CommentServiceImplTest extends TestCase {

    @Autowired
    CommentConverter commentConverter;

    @Test
    void checkConverterComment() {

        CommentResponse commentDto = commentConverter.convertToToResponse(getComment());

        assertNotNull(commentDto.getMessage(), getComment().getMessage());
        assertNotNull(commentDto.getEmail(), getComment().getEmail());
    }

    Comment getComment() {
        Comment comment = new Comment();
        comment.setEmail("dima.maltsev666@gmail.com");
        comment.setId(3L);
        comment.setMessage("message");
        return comment;
    }

}