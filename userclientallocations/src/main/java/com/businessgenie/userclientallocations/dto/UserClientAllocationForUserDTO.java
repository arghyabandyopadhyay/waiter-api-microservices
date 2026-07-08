package com.businessgenie.userclientallocations.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserClientAllocationForUserDTO {
    @JsonProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String id;

    @JsonProperty("outletId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String outletId;

    @JsonProperty("clientId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String clientId;

    @JsonProperty("outletName")
    private String outletName;

    @JsonProperty("outletSalePoint")
    private String outletSalePoint;

    @JsonProperty("clientName")
    private String clientName;

    @JsonProperty("logoURL")
    private String logoURL;

    @JsonProperty("dataExchangeVia")
    private String dataExchangeVia;

    @JsonProperty("dataExchangeURL")
    private String dataExchangeURL;

    @JsonProperty("ucaRoleId")
    private int ucaRoleId;

    public UserClientAllocationForUserDTO(String id, String outletId, String clientId, String outletName, String outletSalePoint,
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
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