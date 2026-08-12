package com.javalife365.javalife365api.exception;

public class EmailDeliveryFailedException extends RuntimeException{

    public EmailDeliveryFailedException(String message) {
        super(message);
    }
}
