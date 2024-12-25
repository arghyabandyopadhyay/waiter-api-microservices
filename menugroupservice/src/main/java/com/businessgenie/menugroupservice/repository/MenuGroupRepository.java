package com.businessgenie.menugroupservice.repository;

import com.businessgenie.menugroupservice.model.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MenuGroupRepository extends JpaRepository<MenuGroup, UUID> {
        List<MenuGroup> findByOutletId(String outletId);

        @Query(value = "SELECT * FROM menu_group WHERE stock_group = :stockGroup AND client_id = :clientId AND outlet_id = :outletId",
                nativeQuery = true)
        MenuGroup findMenuGroup(@Param("stockGroup") String stockGroup, @Param("clientId") String clientId, @Param("outletId") String outletId);
}