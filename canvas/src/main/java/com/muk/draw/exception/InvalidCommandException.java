package com.muk.draw.exception;

public class InvalidCommandException extends RuntimeException {


    private static final long serialVersionUID = -632230194879978L;

    public InvalidCommandException(String message) {
        super(message);
    }


    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }

}
