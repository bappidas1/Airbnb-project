package com.airbnb.dto;

import lombok.Data;

import java.util.Date;


@Data
public class ErrorDetails {
    private String message;
    private Date date;

    public ErrorDetails(String message, Date date) {
        this.message = message;
        this.date = date;
    }
}
