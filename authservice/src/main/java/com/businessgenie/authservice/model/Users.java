package com.businessgenie.authservice.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class Users {
    private UUID id;
    private String name;
    private String emailId;
    private String mobileNumber;
    private boolean isActive = true;
    private String profileUrl;
    private String deviceToken;
    private String jwtToken;
    private LocalDateTime lastLogin = LocalDateTime.now();
    private int roleId = 1;
    private String password;
    private String masterSearchFilter;
}