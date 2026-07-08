package com.businessgenie.orderservice.service;

import com.businessgenie.orderservice.model.Orders;
import com.businessgenie.orderservice.util.exception.NoOrderExistException;
import com.businessgenie.orderservice.util.exception.OrderAlreadyExistsException;
import com.businessgenie.orderservice.util.exception.OrderNotExistsException;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    public Orders createOrder(Orders order) throws OrderAlreadyExistsException;

    public List<Orders> getAllOrders() throws NoOrderExistException;

    public Orders getOrder(UUID uuid) throws OrderNotExistsException;

    public List<Orders> getAllOrdersForUser(String userId) throws NoOrderExistException;

    public Orders updateOrder(Orders order) throws OrderNotExistsException;

    public void deleteOrder(UUID uuid) throws OrderNotExistsException;
}
