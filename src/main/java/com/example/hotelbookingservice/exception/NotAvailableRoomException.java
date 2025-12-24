package com.example.hotelbookingservice.exception;

public class NotAvailableRoomException extends RuntimeException {
    public NotAvailableRoomException(String message) {
        super(message);
    }
}
