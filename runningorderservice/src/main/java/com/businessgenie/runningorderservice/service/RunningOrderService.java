package com.businessgenie.runningorderservice.service;

import com.businessgenie.runningorderservice.constants.OrderType;
import com.businessgenie.runningorderservice.dto.RunningModelUserDetailOutletDetailDTO;
import com.businessgenie.runningorderservice.model.RunningOrder;
import com.businessgenie.runningorderservice.util.exception.NoRunningOrderExistsException;
import com.businessgenie.runningorderservice.util.exception.RunningOrderAlreadyExistsException;
import com.businessgenie.runningorderservice.util.exception.RunningOrderNotExistsException;
import java.util.List;
import java.util.UUID;

public interface RunningOrderService {

    public RunningOrder createRunningOrder(RunningOrder runningOrder) throws RunningOrderAlreadyExistsException;

    public List<RunningOrder> getAllRunningOrders() throws NoRunningOrderExistsException;

    public RunningOrder getRunningOrder(UUID uuid) throws RunningOrderNotExistsException;

    public List<RunningModelUserDetailOutletDetailDTO> getAllRunningOrdersForUser(String userId, OrderType orderType) throws NoRunningOrderExistsException;

    public List<RunningModelUserDetailOutletDetailDTO> getAllRunningOrdersForClient(String clientId) throws NoRunningOrderExistsException;
    public List<RunningModelUserDetailOutletDetailDTO> getAllRunningOrdersInOutletSalePointNameForClient(String clientId, String outletName, String salePointName, String salePointType) throws NoRunningOrderExistsException;

    public RunningOrder updateRunningOrder(RunningOrder runningOrder) throws RunningOrderNotExistsException;

    public void deleteRunningOrder(UUID uuid) throws RunningOrderNotExistsException;
}
