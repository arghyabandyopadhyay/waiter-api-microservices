package com.businessgenie.userclientallocations.dto;

import java.util.UUID;

public class OutletConfigurationModel {
    private UUID outletId;
    private String outletName;
    private String outletSalePoint;
    public OutletConfigurationModel(UUID outletId, String outletName, String outletSalePoint) {
        this.outletId = outletId;
        this.outletName = outletName;
        this.outletSalePoint = outletSalePoint;
    }
    public UUID getOutletId() {
        return outletId;
    }
    public void setOutletId(UUID outletId) {
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
  
