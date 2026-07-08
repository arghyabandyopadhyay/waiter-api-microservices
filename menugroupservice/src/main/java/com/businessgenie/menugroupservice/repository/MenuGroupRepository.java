package com.businessgenie.menugroupservice.repository;

import com.businessgenie.menugroupservice.dto.MenuGroupDTO;
import com.businessgenie.menugroupservice.model.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MenuGroupRepository extends JpaRepository<MenuGroup, UUID> {
        @Query(name = "MenuGroup.findByOutletId",
                nativeQuery = true)
        List<MenuGroupDTO> findByOutletId(@Param("outlet_id") String outletId);
        @Query(name = "MenuGroup.findByClientId",
                nativeQuery = true)
        List<MenuGroupDTO> findByClientId(@Param("client_id") String clientId);

        @Query(value = "SELECT * FROM menu_group WHERE stock_group = :stockGroup AND client_id = :clientId AND outlet_id = :outletId",
                nativeQuery = true)
        MenuGroup findMenuGroup(@Param("stockGroup") String stockGroup, @Param("clientId") String clientId, @Param("outletId") String outletId);
}