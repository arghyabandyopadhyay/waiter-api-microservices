package com.businessgenie.runningorderservice.repository;

import com.businessgenie.runningorderservice.dto.RunningModelUserDetailOutletDetailDTO;
import com.businessgenie.runningorderservice.model.RunningOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RunningOrderRepository extends JpaRepository<RunningOrder, UUID> {
        @Query(name = "RunningOrder.findRunningOrderByWaiterIdAndTerminationStatus", nativeQuery = true)
        List<RunningModelUserDetailOutletDetailDTO> findRunningOrderByUserIdAndTerminationStatus(@Param("waiterId") String userId, @Param("isTerminated") boolean isTerminated);
        
        @Query(name = "RunningOrder.findRunningOrderByWaiterId", nativeQuery = true)
        List<RunningModelUserDetailOutletDetailDTO> findByWaiterId(@Param("waiterId") String userId);

        @Query(name = "RunningOrder.findRunningOrderByClientId", nativeQuery = true)
        List<RunningModelUserDetailOutletDetailDTO> findByClientId(@Param("clientId") String clientId);
        @Query(name = "RunningOrder.findRunningOrderByClientIdOutletIdSalePointTypeAndSalePointName", nativeQuery = true)
        List<RunningModelUserDetailOutletDetailDTO> findByClientIdOutletNameSalePointNameSalePointType(@Param("clientId") String clientId,@Param("outletName") String outletName,@Param("salePointName") String salePointName, @Param("salePointType") String salePointType);

        @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId AND company_guid = :companyGUID AND outlet_id = :outletId",
                nativeQuery = true)
        RunningOrder findUserClientAllocation(@Param("userId") String userId, @Param("companyGUID") String companyGUID, @Param("outletId") String outletId);
}