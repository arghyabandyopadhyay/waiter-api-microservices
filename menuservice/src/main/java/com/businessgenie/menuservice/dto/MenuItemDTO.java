package com.businessgenie.menuservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuItemDTO {

    @JsonProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String id;

    @JsonProperty("itemImage")
    private String itemImage;

    @JsonProperty("item")
    private String item;

    @JsonProperty("itemDescription")
    private String itemDescription;

    @JsonProperty("commentForKOT")
    private String commentForKOT;

    @JsonProperty("stockGroupId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String stockGroupId;

    @JsonProperty("rateBeforeDiscount")
    private double rateBeforeDiscount;

    @JsonProperty("discount")
    private double discount;

    @JsonProperty("rate")
    private double rate;

    @JsonProperty("taxClassId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String taxClassId;

    @JsonProperty("isDiscountable")
    private boolean isDiscountable;

    @JsonProperty("isVeg")
    private boolean isVeg;

    @JsonProperty("taxRate")
    private double taxRate;

    @JsonProperty("tags")
    private String tags;

    @JsonProperty("clientId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String clientId;

    @JsonProperty("favourite")
    private boolean favourite;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("stockGroup")
    private String stockGroup;

    @JsonProperty("outletId")
    private String outletId;

    @JsonProperty("outletName")
    private String outletName;

    // Getters and Setters

    public MenuItemDTO(String id, String itemImage, String item, String itemDescription, String commentForKOT,
            String stockGroupId, double rateBeforeDiscount, double discount, double rate, String taxClassId,
            boolean isDiscountable, boolean isVeg, double taxRate, String tags, String clientId, boolean favourite,
            String imageUrl, String stockGroup, String outletId, String outletName) {
        this.id = id;
        this.itemImage = itemImage;
        this.item = item;
        this.itemDescription = itemDescription;
        this.commentForKOT = commentForKOT;
        this.stockGroupId = stockGroupId;
        this.rateBeforeDiscount = rateBeforeDiscount;
        this.discount = discount;
        this.rate = rate;
        this.taxClassId = taxClassId;
        this.isDiscountable = isDiscountable;
        this.isVeg = isVeg;
        this.taxRate = taxRate;
        this.tags = tags;
        this.clientId = clientId;
        this.favourite = favourite;
        this.imageUrl = imageUrl;
        this.stockGroup = stockGroup;
        this.outletId = outletId;
        this.outletName = outletName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getCommentForKOT() {
        return commentForKOT;
    }

    public void setCommentForKOT(String commentForKOT) {
        this.commentForKOT = commentForKOT;
    }

    public String getStockGroupId() {
        return stockGroupId;
    }

    public void setStockGroupId(String stockGroupId) {
        this.stockGroupId = stockGroupId;
    }

    public double getRateBeforeDiscount() {
        return rateBeforeDiscount;
    }

    public void setRateBeforeDiscount(double rateBeforeDiscount) {
        this.rateBeforeDiscount = rateBeforeDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTaxClassId() {
        return taxClassId;
    }

    public void setTaxClassId(String taxClassId) {
        this.taxClassId = taxClassId;
    }

    public boolean isDiscountable() {
        return isDiscountable;
    }

    public void setDiscountable(boolean discountable) {
        isDiscountable = discountable;
    }

    public boolean isVeg() {
        return isVeg;
    }

    public void setVeg(boolean veg) {
        isVeg = veg;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
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
