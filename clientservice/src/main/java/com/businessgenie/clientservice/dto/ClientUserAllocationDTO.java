package com.businessgenie.clientservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class ClientUserAllocationDTO {
    private UUID id;
    private UUID userClientAllocationId;
    private String name;
    private String mobileNumber;
    private int roleId;
    private boolean isActive;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime lastLogin;
    private UUID outletId;
    private String outletName;
    private int ucaRoleId;


    public ClientUserAllocationDTO(UUID id, UUID userClientAllocationId, String name, String mobileNumber, int roleId,
            boolean isActive, LocalDateTime lastLogin, UUID outletId, String outletName, int ucaRoleId) {
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
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getUserClientAllocationId() {
        return userClientAllocationId;
    }
    public void setUserClientAllocationId(UUID userClientAllocationId) {
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
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
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
    public int getUcaRoleId() {
        return ucaRoleId;
    }
    public void setUcaRoleId(int ucaRoleId) {
        this.ucaRoleId = ucaRoleId;
    }
}
