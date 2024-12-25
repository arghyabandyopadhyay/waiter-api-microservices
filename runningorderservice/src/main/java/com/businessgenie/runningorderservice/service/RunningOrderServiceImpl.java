package com.businessgenie.runningorderservice.service;

import com.businessgenie.runningorderservice.model.RunningOrder;
import com.businessgenie.runningorderservice.repository.RunningOrderRepository;
import com.businessgenie.runningorderservice.util.exception.RunningOrderAlreadyExistsException;
import com.businessgenie.runningorderservice.util.exception.NoRunningOrderExistsException;
import com.businessgenie.runningorderservice.util.exception.RunningOrderNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class RunningOrderServiceImpl implements RunningOrderService {

    @Autowired
    RunningOrderRepository runningOrderRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public RunningOrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RunningOrder createRunningOrder(RunningOrder runningOrder) throws RunningOrderAlreadyExistsException {
        if (runningOrderRepository.existsById(runningOrder.getId())) {
            throw new RunningOrderAlreadyExistsException();
        }
        return runningOrderRepository.save(runningOrder);
    }

    @Override
    public List<RunningOrder> getAllRunningOrders() throws NoRunningOrderExistsException {
        List<RunningOrder> runningOrders = runningOrderRepository.findAll();
        if (runningOrders.isEmpty()) {
            throw new NoRunningOrderExistsException();
        }
        return runningOrders;
    }

    @Override
    public RunningOrder getRunningOrder(UUID uuid) throws RunningOrderNotExistsException {
        return runningOrderRepository.findById(uuid).orElseThrow(RunningOrderNotExistsException::new);
    }

    @Override
    public List<RunningOrder> getAllRunningOrdersForUser(String userId) throws NoRunningOrderExistsException {
        List<RunningOrder> runningOrders = runningOrderRepository.findByUserId(userId);
        if (runningOrders.isEmpty()) {
            throw new NoRunningOrderExistsException();
        }
        return runningOrders;
    }

    @Override
    public RunningOrder updateRunningOrder(RunningOrder runningOrder) throws RunningOrderNotExistsException {
        if (runningOrderRepository.existsById(runningOrder.getId())) {
            return runningOrderRepository.save(runningOrder);
        } else {
            throw new RunningOrderNotExistsException();
        }
    }

    @Override
    public void deleteRunningOrder(UUID uuid) throws RunningOrderNotExistsException {
        if (runningOrderRepository.existsById(uuid)) {
            runningOrderRepository.deleteById(uuid);
        } else {
            throw new RunningOrderNotExistsException();
        }
    }
}
