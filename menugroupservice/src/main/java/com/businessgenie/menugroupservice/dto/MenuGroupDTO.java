package com.businessgenie.menugroupservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class MenuGroupDTO {

    @JsonProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String id;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("stockGroup")
    private String stockGroup;

    @JsonProperty("outletId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String outletId;

    @JsonProperty("outletName")
    private String outletName;

    public MenuGroupDTO(String id, String imageUrl, String stockGroup, String outletId, String outletName) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.stockGroup = stockGroup;
        this.outletId = outletId;
        this.outletName = outletName;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStockGroup() {
        return stockGroup;
    }

    public void setStockGroup(String stockGroup) {
        this.stockGroup = stockGroup;
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
}
