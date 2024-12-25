package com.businessgenie.menuservice.repository;

import com.businessgenie.menuservice.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
        List<MenuItem> findByOutletId(String outletId);
        @Query(value = "SELECT * FROM menu_item WHERE item = :item AND client_id = :clientId AND outlet_id = :outletId",
                nativeQuery = true)
        MenuItem findMenuItem(@Param("item") String item, @Param("clientId") String clientId, @Param("outletId") String outletId);
}