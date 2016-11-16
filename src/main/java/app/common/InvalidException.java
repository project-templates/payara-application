package app.common;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
