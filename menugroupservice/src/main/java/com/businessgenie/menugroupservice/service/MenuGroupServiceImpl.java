package com.businessgenie.menugroupservice.service;

import com.businessgenie.menugroupservice.dto.MenuGroupDTO;
import com.businessgenie.menugroupservice.model.MenuGroup;
import com.businessgenie.menugroupservice.repository.MenuGroupRepository;
import com.businessgenie.menugroupservice.util.exception.MenuGroupAlreadyExistsException;
import com.businessgenie.menugroupservice.util.exception.MenuGroupNotExistsException;
import com.businessgenie.menugroupservice.util.exception.NoMenuGroupExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class MenuGroupServiceImpl implements MenuGroupService {

    @Autowired
    MenuGroupRepository menuGroupRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public MenuGroupServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public MenuGroup updateMenuGroup(MenuGroup users) throws MenuGroupNotExistsException {
        if(menuGroupRepository.existsById(users.getId())) return menuGroupRepository.save(users);
        else throw new MenuGroupNotExistsException();
    }

    @Override
    public void deleteMenuGroup(UUID uuid) throws MenuGroupNotExistsException {
        if(menuGroupRepository.existsById(uuid)) menuGroupRepository.deleteById(uuid);
        else throw new MenuGroupNotExistsException();
    }

    @Override
    public List<MenuGroup> getAllMenuGroup() {
        return menuGroupRepository.findAll();
    }

    @Override
    public MenuGroup getMenuGroup(UUID uuid) throws MenuGroupNotExistsException {
        return menuGroupRepository.findById(uuid).orElseThrow(MenuGroupNotExistsException::new);
    }


    @Override
    public MenuGroup createMenuGroup(MenuGroup userClientAllocation)
            throws MenuGroupAlreadyExistsException {
        if(menuGroupRepository.findMenuGroup(userClientAllocation.getStockGroup(), userClientAllocation.getClientId(), userClientAllocation.getOutletId()) == null)
            return menuGroupRepository.save(userClientAllocation);
        else throw new MenuGroupAlreadyExistsException();
    }


    @Override
    public List<MenuGroupDTO> getAllMenuGroupForOutlet(String outletId)
            throws NoMenuGroupExistsException {
                return menuGroupRepository.findByOutletId(outletId);
            }

    @Override
    public List<MenuGroupDTO> getAllMenuGroupForClient(String clientId)
            throws NoMenuGroupExistsException {
                return menuGroupRepository.findByClientId(clientId);
            }
}
