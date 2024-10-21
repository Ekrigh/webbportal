package com.group6.webbportal.exceptions;

public class TimeSlotAlreadyBookedException extends RuntimeException {
    public TimeSlotAlreadyBookedException() {
    }

    public TimeSlotAlreadyBookedException(String message) {
        super(message);
    }

    public TimeSlotAlreadyBookedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeSlotAlreadyBookedException(Throwable cause) {
        super(cause);
    }
}
