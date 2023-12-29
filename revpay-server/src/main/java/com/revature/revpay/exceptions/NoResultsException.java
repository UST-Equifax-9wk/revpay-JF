package com.revature.revpay.exceptions;

public class NoResultsException extends Exception {
    public NoResultsException() {
        super("No Results Found");
    }

    public NoResultsException(String message) {
        super(message);
    }
}
