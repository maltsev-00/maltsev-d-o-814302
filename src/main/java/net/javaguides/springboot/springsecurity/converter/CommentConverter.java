package net.javaguides.springboot.springsecurity.converter;


import net.javaguides.springboot.springsecurity.model.entity.Comment;
import net.javaguides.springboot.springsecurity.model.response.CommentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentConverter {

    CommentResponse convertToToResponse(Comment comment);
}
