package com.businessgenie.otpservice.model;

import java.util.UUID;

public class OtpVerification {
    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public UUID getCustId() {
        return custId;
    }

    public void setCustId(UUID custId) {
        this.custId = custId;
    }

    private int otp;
    private UUID custId;

    public OtpVerification(int otp, UUID custId) {
        this.otp = otp;
        this.custId = custId;
    }
    public OtpVerification() {
    }
}
