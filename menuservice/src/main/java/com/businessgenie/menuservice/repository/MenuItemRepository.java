package com.businessgenie.menuservice.repository;

import com.businessgenie.menuservice.dto.MenuItemDTO;
import com.businessgenie.menuservice.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {

        @Query(name = "MenuItem.findByOutletId",
                nativeQuery = true)
        List<MenuItemDTO> findByOutletId(@Param("outlet_id")String outletId);

        @Query(name = "MenuItem.findByClientId",
                nativeQuery = true)
        List<MenuItemDTO> findByClientId(@Param("client_id")String clientId);

        @Query(name = "MenuItem.findByUserId",
                nativeQuery = true)
        List<MenuItemDTO> findByUserId(@Param("outlet_id")String outletId,@Param("user_id") String userId);

        @Query(value = "SELECT * FROM menu_item WHERE item = :item AND client_id = :client_id AND outlet_id = :outlet_id",
                nativeQuery = true)
        MenuItem findMenuItem(@Param("item") String item, @Param("client_id") String clientId, @Param("outlet_id") String outletId);
}