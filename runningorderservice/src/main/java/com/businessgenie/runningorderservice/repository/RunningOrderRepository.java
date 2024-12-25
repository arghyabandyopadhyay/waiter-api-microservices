package com.businessgenie.runningorderservice.repository;

import com.businessgenie.runningorderservice.model.RunningOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RunningOrderRepository extends JpaRepository<RunningOrder, UUID> {
    @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId",
            nativeQuery = true)
    List<RunningOrder> findByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId AND company_guid = :companyGUID AND outlet_id = :outletId",
            nativeQuery = true)
    RunningOrder findUserClientAllocation(@Param("userId") String userId, @Param("companyGUID") String companyGUID, @Param("outletId") String outletId);
}