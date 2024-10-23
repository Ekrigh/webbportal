package com.group6.webbportal.exceptions;

public class InvalidBookingException extends RuntimeException{
    public InvalidBookingException(String message) {
        super(message);
    }
}
