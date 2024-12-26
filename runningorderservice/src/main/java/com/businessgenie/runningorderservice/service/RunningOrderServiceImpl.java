package com.businessgenie.runningorderservice.service;

import com.businessgenie.runningorderservice.constants.OrderType;
import com.businessgenie.runningorderservice.dto.RunningModelUserDetailOutletDetailDTO;
import com.businessgenie.runningorderservice.model.RunningOrder;
import com.businessgenie.runningorderservice.repository.RunningOrderRepository;
import com.businessgenie.runningorderservice.util.exception.RunningOrderAlreadyExistsException;
import com.businessgenie.runningorderservice.util.exception.NoRunningOrderExistsException;
import com.businessgenie.runningorderservice.util.exception.RunningOrderNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    public List<RunningModelUserDetailOutletDetailDTO> getAllRunningOrdersForUser(String userId, OrderType orderType) throws NoRunningOrderExistsException {
        List<RunningModelUserDetailOutletDetailDTO> runningOrders=new ArrayList<>();
        switch (orderType) {
            case Current:
                runningOrders = runningOrderRepository.findRunningOrderByUserIdAndTerminationStatus(userId, false);
                break;
            case History:
                runningOrders = runningOrderRepository.findRunningOrderByUserIdAndTerminationStatus(userId, true);
                break;
            case All:
                runningOrders = runningOrderRepository.findByWaiterId(userId);
                break;
        }
        if (runningOrders.isEmpty()) {
            throw new NoRunningOrderExistsException();
        }
        return runningOrders;
    }

    @Override
    public List<RunningModelUserDetailOutletDetailDTO> getAllRunningOrdersForClient(String clientId) throws NoRunningOrderExistsException {
        List<RunningModelUserDetailOutletDetailDTO> runningOrders = runningOrderRepository.findByClientId(clientId);
        if (runningOrders.isEmpty()) {
            throw new NoRunningOrderExistsException();
        }
        return runningOrders;
    }

    @Override
    public List<RunningModelUserDetailOutletDetailDTO> getAllRunningOrdersInOutletSalePointNameForClient(String clientId, String outletName, String salePointName, String salePointType) throws NoRunningOrderExistsException {
        List<RunningModelUserDetailOutletDetailDTO> runningOrders = runningOrderRepository.findByClientIdOutletNameSalePointNameSalePointType(clientId, outletName, salePointName, salePointType);
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
