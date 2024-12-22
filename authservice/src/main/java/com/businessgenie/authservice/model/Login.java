package com.businessgenie.authservice.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Login {
    private String loginMethod;
    private String mobileNumber;
    private String authToken;
    private String otp;
    private String emailId;
    private String password;
    public Login(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }
}