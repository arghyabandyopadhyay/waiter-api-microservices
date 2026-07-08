package com.businessgenie.menugroupservice.service;


import com.businessgenie.menugroupservice.dto.MenuGroupDTO;
import com.businessgenie.menugroupservice.model.MenuGroup;
import com.businessgenie.menugroupservice.util.exception.NoMenuGroupExistsException;
import com.businessgenie.menugroupservice.util.exception.MenuGroupAlreadyExistsException;
import com.businessgenie.menugroupservice.util.exception.MenuGroupNotExistsException;
import java.util.List;
import java.util.UUID;

public interface MenuGroupService {
    public MenuGroup createMenuGroup(MenuGroup users) throws MenuGroupAlreadyExistsException;
    public List<MenuGroup> getAllMenuGroup() throws NoMenuGroupExistsException;
    public MenuGroup getMenuGroup(UUID uuid) throws MenuGroupNotExistsException;
    public List<MenuGroupDTO> getAllMenuGroupForOutlet(String outletId) throws NoMenuGroupExistsException;
    public List<MenuGroupDTO> getAllMenuGroupForClient(String clientId) throws NoMenuGroupExistsException;
    public MenuGroup updateMenuGroup(MenuGroup users)  throws MenuGroupNotExistsException;
    public void deleteMenuGroup(UUID uuid) throws MenuGroupNotExistsException;
}
