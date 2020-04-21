package com.nemnous.datastructures.exceptions;

/**
 * This exception is thrown when an user enters invalid details.
 * @author nemnous.
 *
 */
@SuppressWarnings("serial")
public class InvalidArgumentException extends RuntimeException {

    private final String message;

    /**
     * Constructor with message.
     * @param message
     */
    public InvalidArgumentException(final String message) {
        this.message = message;
    }

    /**
     * Constructor with Throwable cause and message.
     * @param cause
     * @param message
     */
    public InvalidArgumentException(final Throwable cause, final String message) {
        super(cause);
        this.message = message;
    }

    /**
     * @return message
     * returns the value of private class variable message.
     */
    @Override
    public String getMessage() {
        return message;
    }

}
