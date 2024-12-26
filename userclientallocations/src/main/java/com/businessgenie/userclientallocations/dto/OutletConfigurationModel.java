package com.businessgenie.userclientallocations.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class OutletConfigurationModel {

    @JsonProperty("outletId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String outletId;

    @JsonProperty("outletName")
    private String outletName;

    @JsonProperty("outletSalePoint")
    private String outletSalePoint;

    public OutletConfigurationModel(String outletId, String outletName, String outletSalePoint) {
        this.outletId = outletId;
        this.outletName = outletName;
        this.outletSalePoint = outletSalePoint;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
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
}
