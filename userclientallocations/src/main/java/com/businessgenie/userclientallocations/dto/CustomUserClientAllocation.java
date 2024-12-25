package com.businessgenie.userclientallocations.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class CustomUserClientAllocation {
    private UUID id;
    private int ucaRoleId;
    private String clientName;
    private String logoURL;
    private String dataExchangeVia;
    private String dataExchangeURL;
    private String clientType="";
    private UUID clientId;
    private List<OutletConfigurationModel> outletConfiguration;
    public CustomUserClientAllocation(UUID id, int ucaRoleId, String clientName, String logoURL, String dataExchangeVia,
            String dataExchangeURL, String clientType, UUID clientId,
            List<OutletConfigurationModel> outletConfiguration) {
        this.id = id;
        this.ucaRoleId = ucaRoleId;
        this.clientName = clientName;
        this.logoURL = logoURL;
        this.dataExchangeVia = dataExchangeVia;
        this.dataExchangeURL = dataExchangeURL;
        this.clientType = clientType;
        this.clientId = clientId;
        this.outletConfiguration = outletConfiguration;
    }
}
