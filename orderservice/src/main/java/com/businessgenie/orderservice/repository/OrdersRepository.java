package com.businessgenie.orderservice.repository;

import com.businessgenie.orderservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
    @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId",
            nativeQuery = true)
    List<Orders> findByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId AND company_guid = :companyGUID AND outlet_id = :outletId",
            nativeQuery = true)
    Orders findUserClientAllocation(@Param("userId") String userId, @Param("companyGUID") String companyGUID, @Param("outletId") String outletId);
}