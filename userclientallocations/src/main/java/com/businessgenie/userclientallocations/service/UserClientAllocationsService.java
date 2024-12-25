package com.businessgenie.userclientallocations.service;


import com.businessgenie.userclientallocations.dto.UserClientAllocationForUserDTO;
import com.businessgenie.userclientallocations.model.UserClientAllocation;
import com.businessgenie.userclientallocations.util.exception.NoUserClientAllocationExistsException;
import com.businessgenie.userclientallocations.util.exception.UserClientAllocationAlreadyExistsException;
import com.businessgenie.userclientallocations.util.exception.UserClientAllocationNotExistsException;
import java.util.List;
import java.util.UUID;

public interface UserClientAllocationsService {

    public UserClientAllocation createUserClientAllocation(UserClientAllocation users) throws UserClientAllocationAlreadyExistsException;

    public List<UserClientAllocation> getAllUserClientAllocations() throws NoUserClientAllocationExistsException;

    public UserClientAllocation getUserClientAllocation(UUID uuid) throws UserClientAllocationNotExistsException;
    public List<UserClientAllocationForUserDTO> getAllUserClientAllocationForUser(String userId) throws NoUserClientAllocationExistsException;
    public UserClientAllocation updateUserClientAllocation(UserClientAllocation users)  throws UserClientAllocationNotExistsException;

    public void deleteUserClientAllocation(UUID uuid) throws UserClientAllocationNotExistsException;
}
