package com.businessgenie.menuservice.service;

import com.businessgenie.menuservice.dto.MenuItemDTO;
import com.businessgenie.menuservice.model.MenuItem;
import com.businessgenie.menuservice.repository.MenuItemRepository;
import com.businessgenie.menuservice.util.exception.MenuItemAlreadyExistsException; 
import com.businessgenie.menuservice.util.exception.MenuItemNotExistsException;
import com.businessgenie.menuservice.util.exception.NoMenuItemExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemRepository menuItemRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public MenuItemServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public MenuItem updateMenuItem(MenuItem menuItem) throws MenuItemNotExistsException {
        if(menuItemRepository.existsById(menuItem.getId())) return menuItemRepository.save(menuItem);
        else throw new MenuItemNotExistsException();
    }

    @Override
    public void deleteMenuItem(UUID uuid) throws MenuItemNotExistsException {
        if(menuItemRepository.existsById(uuid)) menuItemRepository.deleteById(uuid);
        else throw new MenuItemNotExistsException();
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem getMenuItem(UUID uuid) throws MenuItemNotExistsException {
        return menuItemRepository.findById(uuid).orElseThrow(MenuItemNotExistsException::new);
    }


    @Override
    public MenuItem createMenuItem(MenuItem menuItem)
            throws MenuItemAlreadyExistsException {
        if(menuItemRepository.findMenuItem(menuItem.getItem(), menuItem.getClientId(), menuItem.getOutletId()) == null)
            return menuItemRepository.save(menuItem);
        else throw new MenuItemAlreadyExistsException();
    }


    @Override
    public List<MenuItemDTO> getAllMenuItemForOutlet(String outletId)throws NoMenuItemExistsException 
    {
        return menuItemRepository.findByOutletId(outletId);
    }

    @Override
    public List<MenuItemDTO> getAllMenuItemForClient(String clientId)throws NoMenuItemExistsException 
    {
        return menuItemRepository.findByClientId(clientId);
    }

    @Override
    public List<MenuItemDTO> getAllMenuItemForUser(String outletId, String userId)throws NoMenuItemExistsException 
    {
        return menuItemRepository.findByUserId(outletId,userId);
    }
}
