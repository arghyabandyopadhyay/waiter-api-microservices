package com.businessgenie.userclientallocations.model;

import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class CustomUserClientAllocation {
    private UUID id;
    private String companyGUID;
    private int ucaRoleId;
    private String clientName;
    private String logoURL;
    private String dataExchangeVia;
    private String dataExchangeURL;
    private String clientType;
    private String guid;
    private List<OutletConfigurationModel> outletConfiguration;
}
