package com.businessgenie.taxclassservice.repository;

import com.businessgenie.taxclassservice.model.TaxClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaxClassRepository extends JpaRepository<TaxClass, UUID> {
    @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId",
            nativeQuery = true)
    List<TaxClass> findByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId AND company_guid = :companyGUID AND outlet_id = :outletId",
            nativeQuery = true)
    TaxClass findUserClientAllocation(@Param("userId") String userId, @Param("companyGUID") String companyGUID, @Param("outletId") String outletId);
}