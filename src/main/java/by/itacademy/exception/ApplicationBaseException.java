package by.itacademy.exception;

public class ApplicationBaseException extends RuntimeException {

    public ApplicationBaseException() {
        super();
    }

    public ApplicationBaseException(String message) {
        super(message);
    }

    public ApplicationBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
