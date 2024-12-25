package com.businessgenie.userclientallocations.repository;

import com.businessgenie.userclientallocations.model.UserClientAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserClientAllocationsRepository extends JpaRepository<UserClientAllocation, UUID> {
    @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId",
            nativeQuery = true)
    List<UserClientAllocation> findByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId AND company_guid = :companyGUID AND outlet_id = :outletId",
            nativeQuery = true)
    UserClientAllocation findUserClientAllocation(@Param("userId") String userId, @Param("companyGUID") String companyGUID, @Param("outletId") String outletId);
}