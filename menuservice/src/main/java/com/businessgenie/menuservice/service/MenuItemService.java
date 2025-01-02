package com.businessgenie.menuservice.service;


import com.businessgenie.menuservice.dto.MenuItemDTO;
import com.businessgenie.menuservice.model.MenuItem;
import com.businessgenie.menuservice.util.exception.NoMenuItemExistsException;
import com.businessgenie.menuservice.util.exception.MenuItemAlreadyExistsException;
import com.businessgenie.menuservice.util.exception.MenuItemNotExistsException;
import java.util.List;
import java.util.UUID;

public interface MenuItemService {
    public MenuItem createMenuItem(MenuItem users) throws MenuItemAlreadyExistsException;
    public List<MenuItem> getAllMenuItems() throws NoMenuItemExistsException;
    public MenuItem getMenuItem(UUID uuid) throws MenuItemNotExistsException;
    public List<MenuItemDTO> getAllMenuItemForOutlet(String outletId) throws NoMenuItemExistsException;
    public List<MenuItemDTO> getAllMenuItemForClient(String clientId) throws NoMenuItemExistsException;
    public List<MenuItemDTO> getAllMenuItemForUser(String outletId, String userId) throws NoMenuItemExistsException;
    public MenuItem updateMenuItem(MenuItem users)  throws MenuItemNotExistsException;
    public void deleteMenuItem(UUID uuid) throws MenuItemNotExistsException;
}
