package com.businessgenie.orderservice.service;

import com.businessgenie.orderservice.model.Orders;
import com.businessgenie.orderservice.repository.OrdersRepository;
import com.businessgenie.orderservice.util.exception.NoOrderExistException;
import com.businessgenie.orderservice.util.exception.OrderAlreadyExistsException;
import com.businessgenie.orderservice.util.exception.OrderNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrdersRepository ordersRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Orders createOrder(Orders order) throws OrderAlreadyExistsException {
        if (ordersRepository.existsById(order.getId())) {
            throw new OrderAlreadyExistsException();
        }
        return ordersRepository.save(order);
    }

    @Override
    public List<Orders> getAllOrders() throws NoOrderExistException {
        List<Orders> orders = ordersRepository.findAll();
        if (orders.isEmpty()) {
            throw new NoOrderExistException();
        }
        return orders;
    }

    @Override
    public Orders getOrder(UUID uuid) throws OrderNotExistsException {
        return ordersRepository.findById(uuid).orElseThrow(OrderNotExistsException::new);
    }

    @Override
    public List<Orders> getAllOrdersForUser(String userId) throws NoOrderExistException {
        List<Orders> orders = ordersRepository.findByUserId(userId);
        if (orders.isEmpty()) {
            throw new NoOrderExistException();
        }
        return orders;
    }

    @Override
    public Orders updateOrder(Orders order) throws OrderNotExistsException {
        if (ordersRepository.existsById(order.getId())) {
            return ordersRepository.save(order);
        } else {
            throw new OrderNotExistsException();
        }
    }

    @Override
    public void deleteOrder(UUID uuid) throws OrderNotExistsException {
        if (ordersRepository.existsById(uuid)) {
            ordersRepository.deleteById(uuid);
        } else {
            throw new OrderNotExistsException();
        }
    }
}
