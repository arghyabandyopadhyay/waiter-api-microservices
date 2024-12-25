package com.businessgenie.maxtakeawayservice.repository;

import com.businessgenie.maxtakeawayservice.model.MaxTakeAway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface MaxTakeAwayRepository extends JpaRepository<MaxTakeAway, UUID> {
    @Query(value = "SELECT * FROM user_client_allocation WHERE user_id = :userId",
            nativeQuery = true)
    List<MaxTakeAway> findByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM max_take_away WHERE outlet_id = :outlet_id AND current_date = :current_date",
            nativeQuery = true)
    MaxTakeAway findMaxTakeAway(@Param("outlet_id") String outletId, @Param("current_date") Date currentDate);
}