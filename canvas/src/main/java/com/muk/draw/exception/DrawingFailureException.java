package com.muk.draw.exception;

public class DrawingFailureException extends RuntimeException {


    private static final long serialVersionUID = -632230194878878L;

    public DrawingFailureException(String message) {
        super(message);
    }


    public DrawingFailureException(String message, Throwable cause) {
        super(message, cause);
    }

}
