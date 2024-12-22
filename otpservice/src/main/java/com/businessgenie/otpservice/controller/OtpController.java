package com.businessgenie.otpservice.controller;

import com.businessgenie.otpservice.model.OtpVerification;
import com.businessgenie.otpservice.model.Users;
import com.businessgenie.otpservice.service.OtpService;
import com.businessgenie.otpservice.template.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/otp")
public class OtpController {
    /*
     * Autowiring should be implemented for the NewsService. Please note that we
     * should not create any object using the new keyword
     */
    @Autowired
    public OtpService otpService;
    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody() OtpVerification otpVerification){
        try {
            //Generate The Template to send OTP
            int otp = otpService.getOtp(otpVerification.getCustId().toString());
            if(otp==otpVerification.getOtp())return new ResponseEntity<>(HttpStatus.CREATED);
            else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/generateOtp")
    public ResponseEntity<?> sendOtp(@RequestBody() Users user){
        try {
            //Generate The Template to send OTP
            int otp = otpService.generateOtp(user.getId().toString());
            EmailTemplate template = new EmailTemplate();
            StringBuilder stringOtp= new StringBuilder(String.valueOf(otp));
            if(stringOtp.length()<6){
                for(int i=0;i<6-stringOtp.length();i++){
                    stringOtp.insert(0,"0");
                }
            }
            String message = template.template.formatted(user.getName(),stringOtp.toString());
            otpService.sendOtp(user.getEmailId(), message,String.format("Your OTP for Secure Login is %s  - DO NOT REPLY",stringOtp) );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
