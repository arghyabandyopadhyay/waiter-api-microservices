package com.businessgenie.userclientallocations.service;

import com.businessgenie.userclientallocations.dto.ClientUserAllocationDTO;
import com.businessgenie.userclientallocations.dto.UserClientAllocationForUserDTO;
import com.businessgenie.userclientallocations.model.UserClientAllocation;
import com.businessgenie.userclientallocations.repository.UserClientAllocationsRepository;
import com.businessgenie.userclientallocations.util.exception.UserClientAllocationAlreadyExistsException;
import com.businessgenie.userclientallocations.util.exception.NoUserClientAllocationExistsException;
import com.businessgenie.userclientallocations.util.exception.UserClientAllocationNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class UserClientAllocationsServiceImpl implements UserClientAllocationsService {

    @Autowired
    UserClientAllocationsRepository userClientAllocationsRepository;
    private final RestTemplate restTemplate;
    @Autowired
    public UserClientAllocationsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public UserClientAllocation updateUserClientAllocation(UserClientAllocation users) throws UserClientAllocationNotExistsException {
        if(userClientAllocationsRepository.existsById(users.getId())) return userClientAllocationsRepository.save(users);
        else throw new UserClientAllocationNotExistsException();
    }

    @Override
    public void deleteUserClientAllocation(UUID uuid) throws UserClientAllocationNotExistsException {
        if(userClientAllocationsRepository.existsById(uuid)) userClientAllocationsRepository.deleteById(uuid);
        else throw new UserClientAllocationNotExistsException();
    }

    @Override
    public List<UserClientAllocation> getAllUserClientAllocations() {
        return userClientAllocationsRepository.findAll();
    }

    @Override
    public UserClientAllocation getUserClientAllocation(UUID uuid) throws UserClientAllocationNotExistsException {
        return userClientAllocationsRepository.findById(uuid).orElseThrow(UserClientAllocationNotExistsException::new);
    }


    @Override
    public UserClientAllocation createUserClientAllocation(UserClientAllocation userClientAllocation)
            throws UserClientAllocationAlreadyExistsException {
        if(userClientAllocationsRepository.findUserClientAllocation(userClientAllocation.getUserId(), userClientAllocation.getOutletId()) == null)
            return userClientAllocationsRepository.save(userClientAllocation);
        else throw new UserClientAllocationAlreadyExistsException();
    }


    @Override
    public List<UserClientAllocationForUserDTO> getAllUserClientAllocationForUser(String userId)
            throws NoUserClientAllocationExistsException {
                return userClientAllocationsRepository.findByUserId(userId);
            }


    @Override
    public List<ClientUserAllocationDTO> getAllUserClientAllocationForClient(String clientId)
            throws NoUserClientAllocationExistsException {
                return userClientAllocationsRepository.findByClientId(clientId);
            }
}
