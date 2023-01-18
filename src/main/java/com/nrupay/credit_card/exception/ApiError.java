package com.nrupay.credit_card.exception;

public class ApiError {
    private String message;
    private String fieldName;
    public ApiError(String message) {
        this.message = message;
//        this.fieldName = fieldName;
    }
}
