package com.businessgenie.userclientallocations.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomUserClientAllocation {

    @JsonProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String id;

    @JsonProperty("ucaRoleId")
    private int ucaRoleId;

    @JsonProperty("clientName")
    private String clientName;

    @JsonProperty("logoURL")
    private String logoURL;

    @JsonProperty("dataExchangeVia")
    private String dataExchangeVia;

    @JsonProperty("dataExchangeURL")
    private String dataExchangeURL;

    @JsonProperty("clientType")
    private String clientType = "";

    @JsonProperty("clientId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String clientId;

    @JsonProperty("outletConfiguration")
    private List<OutletConfigurationModel> outletConfiguration;

    public CustomUserClientAllocation(String id, int ucaRoleId, String clientName, String logoURL, String dataExchangeVia,
                                      String dataExchangeURL, String clientType, String clientId,
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

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUcaRoleId() {
        return ucaRoleId;
    }

    public void setUcaRoleId(int ucaRoleId) {
        this.ucaRoleId = ucaRoleId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getDataExchangeVia() {
        return dataExchangeVia;
    }

    public void setDataExchangeVia(String dataExchangeVia) {
        this.dataExchangeVia = dataExchangeVia;
    }

    public String getDataExchangeURL() {
        return dataExchangeURL;
    }

    public void setDataExchangeURL(String dataExchangeURL) {
        this.dataExchangeURL = dataExchangeURL;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<OutletConfigurationModel> getOutletConfiguration() {
        return outletConfiguration;
    }

    public void setOutletConfiguration(List<OutletConfigurationModel> outletConfiguration) {
        this.outletConfiguration = outletConfiguration;
    }
}
