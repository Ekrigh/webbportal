package com.group6.webbportal.exceptions;

public class CourtAlreadyExistsException extends RuntimeException{

        public CourtAlreadyExistsException() {
        }

        public CourtAlreadyExistsException(String message) {
            super(message);
        }

        public CourtAlreadyExistsException(String message, Throwable cause) {
            super(message, cause);
        }

        public CourtAlreadyExistsException(Throwable cause) {
            super(cause);
        }
}
