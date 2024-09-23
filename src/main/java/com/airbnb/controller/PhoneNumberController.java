package com.airbnb.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/phone")
public class PhoneNumberController {

    //http://localhost:8080/api/phone/parse
    @GetMapping("/parse")
    public PhoneNumberResponse parsePhoneNumber(@RequestParam String phoneNumber) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumberResponse response = new PhoneNumberResponse();

        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, "");
            response.setCountryCode(numberProto.getCountryCode());
            response.setNationalNumber(numberProto.getNationalNumber());
            response.setValid(phoneUtil.isValidNumber(numberProto));
        } catch (NumberParseException e) {
            throw new RuntimeException("Invalid phone number format: " + phoneNumber);
        }

        return response;
    }
}

// DTO class for response
class PhoneNumberResponse {
    private int countryCode;
    private long nationalNumber;
    private boolean isValid;

    // Getters and Setters
    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public long getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(long nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}

