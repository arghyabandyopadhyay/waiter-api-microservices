package com.businessgenie.runningorderservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RunningModelUserDetailOutletDetailDTO {

    @JsonProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String id;

    @JsonProperty("isTerminated")
    private boolean isTerminated;

    @JsonProperty("name")
    private String name;

    @JsonProperty("mobileNo")
    private String mobileNo;

    @JsonProperty("salePointType")
    private String salePointType;

    @JsonProperty("salePointName")
    private String salePointName;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("pax")
    private Integer pax;

    @JsonProperty("activeSince")
    private LocalDateTime activeSince;

    @JsonProperty("billPrinted")
    private boolean billPrinted;

    @JsonProperty("outletName")
    private String outletName;

    @JsonProperty("outletId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String outletId;

    @JsonProperty("waiterMobileNo")
    private String waiterMobileNo;

    @JsonProperty("waiterName")
    private String waiterName;

    public RunningModelUserDetailOutletDetailDTO(String id, boolean isTerminated, String name, String mobileNo, String salePointType,
                                                 String salePointName, double amount, Integer pax, LocalDateTime activeSince,
                                                 boolean billPrinted, String outletName, String outletId, String waiterMobileNo,
                                                 String waiterName) {
        this.id = id;
        this.isTerminated = isTerminated;
        this.name = name;
        this.mobileNo = mobileNo;
        this.salePointType = salePointType;
        this.salePointName = salePointName;
        this.amount = amount;
        this.pax = pax;
        this.activeSince = activeSince;
        this.billPrinted = billPrinted;
        this.outletName = outletName;
        this.outletId = outletId;
        this.waiterMobileNo = waiterMobileNo;
        this.waiterName = waiterName;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean isTerminated) {
        this.isTerminated = isTerminated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSalePointType() {
        return salePointType;
    }

    public void setSalePointType(String salePointType) {
        this.salePointType = salePointType;
    }

    public String getSalePointName() {
        return salePointName;
    }

    public void setSalePointName(String salePointName) {
        this.salePointName = salePointName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getPax() {
        return pax;
    }

    public void setPax(Integer pax) {
        this.pax = pax;
    }

    public LocalDateTime getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(LocalDateTime activeSince) {
        this.activeSince = activeSince;
    }

    public boolean isBillPrinted() {
        return billPrinted;
    }

    public void setBillPrinted(boolean billPrinted) {
        this.billPrinted = billPrinted;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getWaiterMobileNo() {
        return waiterMobileNo;
    }

    public void setWaiterMobileNo(String waiterMobileNo) {
        this.waiterMobileNo = waiterMobileNo;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }
}