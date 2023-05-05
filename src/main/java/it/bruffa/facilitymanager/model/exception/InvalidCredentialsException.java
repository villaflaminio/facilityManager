package it.bruffa.facilitymanager.model.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(String.format(message));
    }
    public InvalidCredentialsException(Throwable cause) {
        super(cause);
    }
    public InvalidCredentialsException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
