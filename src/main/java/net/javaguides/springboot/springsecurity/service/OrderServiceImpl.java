package net.javaguides.springboot.springsecurity.service;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.springsecurity.converter.CommentConverter;
import net.javaguides.springboot.springsecurity.model.entity.Order;
import net.javaguides.springboot.springsecurity.model.response.CommentResponse;
import net.javaguides.springboot.springsecurity.repository.HairCutRepository;
import net.javaguides.springboot.springsecurity.repository.OrderRepos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final HairCutRepository fileRepository;
    private final OrderRepos orderRepos;
    private final CommentConverter commentConverter;

    @Override
    public void save(Long id, String email) {
        Order order = Order.builder()
                .orderId(id)
                .emailUser(email)
                .build();
        orderRepos.save(order);
    }

    @Override
    public List<CommentResponse> findById(Long id) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepos.getOne(id);
        orderRepos.delete(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepos.findAll();
    }

    @Override
    public Order getOne(Long id) {
        return orderRepos.getOne(id);
    }
}
