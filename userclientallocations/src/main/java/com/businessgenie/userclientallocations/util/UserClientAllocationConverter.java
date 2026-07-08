package com.businessgenie.userclientallocations.util;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import com.businessgenie.userclientallocations.dto.OutletConfigurationModel;

import com.businessgenie.userclientallocations.dto.CustomUserClientAllocation;
import com.businessgenie.userclientallocations.dto.UserClientAllocationForUserDTO;

public class UserClientAllocationConverter {

    public static List<CustomUserClientAllocation> convertAndGroupByClientId(List<UserClientAllocationForUserDTO> dtos) {
        // Group by clientId
        Map<String, List<UserClientAllocationForUserDTO>> groupedByClientId = dtos.stream()
                .collect(Collectors.groupingBy(UserClientAllocationForUserDTO::getClientId));

        // Convert to List<CustomUserClientAllocation>
        return groupedByClientId.entrySet().stream()
                .map(entry -> new CustomUserClientAllocation(entry.getKey(), entry.getValue().get(0).getUcaRoleId(),
                        entry.getValue().get(0).getClientName(), entry.getValue().get(0).getLogoURL(),
                        entry.getValue().get(0).getDataExchangeVia(), entry.getValue().get(0).getDataExchangeURL(),
                        "", entry.getValue().get(0).getClientId(),
                        entry.getValue().stream().map(dto -> new OutletConfigurationModel(dto.getOutletId(),
                                dto.getOutletName(), dto.getOutletSalePoint())).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}