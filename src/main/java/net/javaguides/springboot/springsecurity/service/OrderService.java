package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.dto.CommentDto;
import net.javaguides.springboot.springsecurity.model.entity.Order;
import net.javaguides.springboot.springsecurity.model.response.CommentResponse;

import java.util.List;

public interface OrderService {
    void save(Long id,String email);

    List<CommentResponse> findById(Long id);

    void deleteOrder(Long id);

    List<Order> getAll();

    Order getOne(Long id);
}
