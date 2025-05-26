package com.businessgenie.userclientallocations.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ClientUserAllocationDTO {

    @JsonProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String id;

    @JsonProperty("userClientAllocationId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String userClientAllocationId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("roleId")
    private int roleId;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("lastLogin")
    private LocalDateTime lastLogin;

    @JsonProperty("outletId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String outletId;

    @JsonProperty("outletName")
    private String outletName;

    @JsonProperty("ucaRoleId")
    private int ucaRoleId;

    public ClientUserAllocationDTO(String id, String userClientAllocationId, String name, String mobileNumber, int roleId,
                                   boolean isActive, LocalDateTime lastLogin, String outletId, String outletName, int ucaRoleId) {
        this.id = id;
        this.userClientAllocationId = userClientAllocationId;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.roleId = roleId;
        this.isActive = isActive;
        this.lastLogin = lastLogin;
        this.outletId = outletId;
        this.outletName = outletName;
        this.ucaRoleId = ucaRoleId;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserClientAllocationId() {
        return userClientAllocationId;
    }

    public void setUserClientAllocationId(String userClientAllocationId) {
        this.userClientAllocationId = userClientAllocationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
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

    public int getUcaRoleId() {
        return ucaRoleId;
    }

    public void setUcaRoleId(int ucaRoleId) {
        this.ucaRoleId = ucaRoleId;
    }
}
