package net.javaguides.springboot.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springsecurity.ForbiddenException;
import net.javaguides.springboot.springsecurity.model.entity.Order;
import net.javaguides.springboot.springsecurity.model.response.CommentResponse;
import net.javaguides.springboot.springsecurity.service.OrderService;
import net.javaguides.springboot.springsecurity.service.UserLogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders/")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    @Value("${email}")
    private String email;

    @PostMapping("{id}")
    public void saveOrder(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        if (token.isEmpty()) {
            throw new ForbiddenException("Token is null");
        }
        orderService.save(id, email);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        if (token.isEmpty()) {
            throw new ForbiddenException("Token is null");
        }
         orderService.deleteOrder(id);
    }

    @GetMapping
    public List<Order> listOrder(){
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") Long id){
        return orderService.getOne(id);
    }

}
