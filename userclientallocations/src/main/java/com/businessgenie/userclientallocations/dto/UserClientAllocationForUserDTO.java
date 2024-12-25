package com.businessgenie.userclientallocations.dto;

import java.util.UUID;

public class UserClientAllocationForUserDTO {
    private UUID id;
    private UUID outletId;
    private UUID clientId;
    private String outletName;
    private String outletSalePoint;
    private String clientName;
    private String logoURL;
    private String dataExchangeVia;
    private String dataExchangeURL;
    private int ucaRoleId;
    public UserClientAllocationForUserDTO(UUID id, UUID outletId, UUID clientId, String outletName, String outletSalePoint,
            String clientName, String logoURL, String dataExchangeVia, String dataExchangeURL, int ucaRoleId) {
        this.id = id;
        this.outletId = outletId;
        this.clientId = clientId;
        this.outletName = outletName;
        this.outletSalePoint = outletSalePoint;
        this.clientName = clientName;
        this.logoURL = logoURL;
        this.dataExchangeVia = dataExchangeVia;
        this.dataExchangeURL = dataExchangeURL;
        this.ucaRoleId = ucaRoleId;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getOutletId() {
        return outletId;
    }
    public void setOutletId(UUID outletId) {
        this.outletId = outletId;
    }
    public UUID getClientId() {
        return clientId;
    }
    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
    public String getOutletName() {
        return outletName;
    }
    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }
    public String getOutletSalePoint() {
        return outletSalePoint;
    }
    public void setOutletSalePoint(String outletSalePoint) {
        this.outletSalePoint = outletSalePoint;
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
    public int getUcaRoleId() {
        return ucaRoleId;
    }
    public void setUcaRoleId(int ucaRoleId) {
        this.ucaRoleId = ucaRoleId;
    }
}